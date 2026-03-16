package com.example.impl.domain

import com.example.impl.data.Track

internal interface TracksRepository {

    suspend fun loadTracks(): List<Track>

}