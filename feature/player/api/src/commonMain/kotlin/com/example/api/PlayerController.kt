package com.example.api

import kotlinx.coroutines.flow.StateFlow

interface PlayerController {

    val playbackState: StateFlow<PlaybackState>
    fun play(track: Track)
}