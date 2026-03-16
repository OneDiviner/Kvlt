package com.example.impl.data

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.net.toUri
import com.example.impl.domain.TracksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal actual class TracksRepositoryImpl actual constructor(): TracksRepository, KoinComponent {

    private val contentResolver: ContentResolver by inject()

    private val uri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
    } else {
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    }

    private val projection = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.ALBUM_ID,
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.Media.ALBUM,
        MediaStore.Audio.Media.DATA,
    )

        private val selection = buildString {
            append("${MediaStore.Audio.Media.IS_MUSIC} != 0")
            append(" AND ${MediaStore.Audio.Media.IS_RINGTONE} == 0")
            append(" AND ${MediaStore.Audio.Media.IS_ALARM} == 0")
            append(" AND ${MediaStore.Audio.Media.IS_NOTIFICATION} == 0")
            append(" AND ${MediaStore.Audio.Media.ARTIST} IS NOT NULL")
            append(" AND ${MediaStore.Audio.Media.ARTIST} != ''")
            append(" AND ${MediaStore.Audio.Media.ARTIST} NOT LIKE '%unknown%'")
            append(" AND ${MediaStore.Audio.Media.ARTIST} NOT LIKE '%<unknown>%'")
            append(" AND ${MediaStore.Audio.Media.ARTIST} NOT LIKE 'null'")
        }

    private val query = contentResolver.query(
        uri,
        projection,
        selection,
        null,
        null
    )

    private fun getArtworkUri(albumId: Long): String? {

        if (albumId <= 0) return null

        return try {

            val albumUri = "content://media/external/audio/albumart".toUri()
            val artworkUri = ContentUris.withAppendedId(albumUri, albumId)

            contentResolver.openInputStream(artworkUri)?.use {
                artworkUri.toString()
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun getGenreList(audioId: Long): List<Genre>? {

        val genres = mutableListOf<Genre>()

        return try {
            val genreCursor = contentResolver.query(
                MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Audio.Genres._ID, MediaStore.Audio.Genres.NAME),
                null, null, null
            )

            genreCursor?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Genres._ID)
                val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Genres.NAME)

                while (cursor.moveToNext()) {
                    val genreId = cursor.getLong(idColumn)
                    val genreName = cursor.getString(nameColumn) ?: continue

                    val membersUri = MediaStore.Audio.Genres.Members.getContentUri(
                        "external", genreId
                    )

                    val membersCursor = contentResolver.query(
                        membersUri,
                        arrayOf(MediaStore.Audio.Media._ID),
                        "${MediaStore.Audio.Media._ID} = ?",
                        arrayOf(audioId.toString()),
                        null
                    )

                    membersCursor?.use { members ->
                        if (members.moveToFirst()) {
                            genres.add(
                                Genre(
                                    id = genreId.toString(),
                                    name = genreName
                                )
                            )
                        }
                    }
                }
            }
            genres
        } catch (e: Exception) {
            null
        }
    }

    actual override suspend fun loadTracks(): List<Track> = withContext(Dispatchers.IO) {

        val trackList = mutableListOf<Track>()

        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val albumIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val title = cursor.getString(titleColumn)
                val artist = cursor.getString(artistColumn)
                val albumTitle = cursor.getString(albumColumn)
                val albumId = cursor.getLong(albumIdColumn)
                val duration = cursor.getLong(durationColumn)

                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                val artworkUri = getArtworkUri(albumId)
                val genreList = getGenreList(id)

                trackList.add(
                    Track(
                        id = id.toString(),
                        uri = contentUri.toString(),
                        title = title,
                        artist = artist,
                        genreList = genreList,
                        duration = duration,
                        albumTitle = albumTitle,
                        artworkUri = artworkUri
                    )
                )
            }
        }

        return@withContext trackList
    }
}