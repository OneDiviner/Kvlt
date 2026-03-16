package com.example.impl.data

import com.example.impl.domain.TracksRepository

internal expect class TracksRepositoryImpl(): TracksRepository {

    override suspend fun loadTracks(): List<Track>

}