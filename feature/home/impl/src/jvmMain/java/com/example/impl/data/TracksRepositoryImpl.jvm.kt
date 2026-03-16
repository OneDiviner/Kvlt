package com.example.impl.data

import com.example.impl.domain.TracksRepository

internal actual class TracksRepositoryImpl actual constructor() :
    TracksRepository {
    actual override suspend fun loadTracks(): List<Track> {
        TODO("Not yet implemented")
    }
}