package com.example.impl.domain

import com.example.api.Track

interface TracksRepository {

    suspend fun loadTracks(): List<Track>

}