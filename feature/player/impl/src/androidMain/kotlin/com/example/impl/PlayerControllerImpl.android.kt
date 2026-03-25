package com.example.impl

import android.content.ComponentName
import android.content.Context
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Tracks
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.example.api.PlayerController
import com.example.api.Track
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import androidx.core.net.toUri
import androidx.media3.common.MediaMetadata
import com.example.api.PlaybackState
import com.example.api.PlaybackStatus
import com.example.api.PlayerIntent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//TODO: Remove from this package
fun MediaItem.toTrack(duration: Long? = null) = Track(
    id = this.mediaId,
    uri = this.requestMetadata.mediaUri?.toString() ?: "", //TODO: Придуммать что сделать в случае если вернулся Uri == null
    title = this.mediaMetadata.title?.toString(),
    artist = this.mediaMetadata.artist?.toString(),
    genreList = null,
    duration = duration,
    albumTitle = this.mediaMetadata.albumTitle?.toString(),
    albumArtUri = this.mediaMetadata.artworkUri?.toString(),
)

actual class PlayerControllerImpl actual constructor() : PlayerController, KoinComponent {

    private val context: Context by inject()

    private var mediaController: MediaController? = null

    private val _playbackState = MutableStateFlow(PlaybackState())
    actual override val playbackState: StateFlow<PlaybackState> = _playbackState

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private var currentPositionJob: Job? = null

    init {
        initialize()
    }

    private fun initialize() {
        val sessionToken = SessionToken(
            context,
            ComponentName(
                context,
                PlaybackService::class.java
            )
        )
        val controllerFuture: ListenableFuture<MediaController> = MediaController.Builder(context, sessionToken).buildAsync()

        controllerFuture.addListener(
            {
                mediaController = controllerFuture.get()
                mediaController?.addListener(playerListeners())
                updatePlaybackState()
            },
            MoreExecutors.directExecutor()
        )

    }

    private fun playerListeners() = object:
        Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                updatePlaybackState()
            }
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                updatePlaybackState()
                if (isPlaying) {
                    startPositionObserving()
                } else {
                    stopPositionObserving()
                }
            }
            override fun onPlaybackStateChanged(playbackState: Int) {
                updatePlaybackState()
            }

        override fun onPositionDiscontinuity(
            oldPosition: Player.PositionInfo,
            newPosition: Player.PositionInfo,
            reason: Int
        ) {
            updatePlaybackState()
        }
            override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                _playbackState.value = _playbackState.value.copy(
                    playbackStatus = PlaybackStatus.ERROR,
                )
            }
        }

    private fun updatePlaybackState() {
        _playbackState.value = PlaybackState(
            currentTrack = mediaController?.currentMediaItem?.toTrack(
                duration = mediaController?.duration?.let {
                    if (it > 0) mediaController?.duration else null
                }
            ),
            currentPosition = mediaController?.currentPosition,
            isPlaying = mediaController?.isPlaying ?: false,
            playbackStatus = when(mediaController?.playbackState) {
                Player.STATE_IDLE -> PlaybackStatus.IDLE
                Player.STATE_BUFFERING -> PlaybackStatus.BUFFERING
                Player.STATE_READY -> PlaybackStatus.READY
                Player.STATE_ENDED -> PlaybackStatus.ENDED
                else -> PlaybackStatus.ERROR
            }
        )
    }

    actual override fun handleIntent(playerIntent: PlayerIntent) {
        when(playerIntent) {
            is PlayerIntent.Play -> play(playerIntent.track)
            is PlayerIntent.Pause -> pause()
            is PlayerIntent.Resume -> resume()
            is PlayerIntent.Next -> next()
            is PlayerIntent.Previous -> previous()
            is PlayerIntent.SeekTo -> seekTo(playerIntent.position)
        }
    }

    private fun play(track: Track) {

        //TODO: Check logic of create Meatadata
        val metadata = MediaMetadata.Builder()
            .setTitle(track.title)
            .setArtist(track.artist)
            .setAlbumTitle(track.albumTitle)
            .setArtworkUri(track.albumArtUri?.toUri())
            .setGenre(track.genreList?.joinToString(", "))
            .setDisplayTitle(track.title)
            .setExtras(android.os.Bundle().apply {
                track.duration?.let { putLong("DURATION_MS", it) }
            })
            .build()

        val mediaItem = MediaItem.Builder()
            .setMediaId(track.id)
            .setUri(track.uri)
            .setMediaMetadata(metadata)
            .build()

        mediaController?.apply {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    private fun pause() {
        mediaController?.pause()
    }

    private fun resume() {
        mediaController?.play()
    }

    private fun next() {
        mediaController?.seekToNext()
    }

    private fun previous() {
        mediaController?.seekToPrevious()
    }

    private fun seekTo(position: Long) {
        mediaController?.seekTo(position)
    }

    private fun startPositionObserving() {
        stopPositionObserving()
        currentPositionJob = scope.launch {
            while (true) {
                if (mediaController?.isPlaying == true) {
                    updatePlaybackState()
                }
                println("Position is ${_playbackState.value.currentPosition}")
                println("Duration is ${_playbackState.value.currentTrack?.duration}")
                delay(1000)
            }
        }
    }
    private fun stopPositionObserving() {
        currentPositionJob?.cancel()
    }
}