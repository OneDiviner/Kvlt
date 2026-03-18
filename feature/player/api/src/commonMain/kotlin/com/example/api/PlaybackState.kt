package com.example.api

import kotlinx.serialization.Serializable

@Serializable
data class PlaybackState(
    val currentTrack: Track? = null,
    val isPlaying: Boolean = false,
    val playbackStatus: PlaybackStatus = PlaybackStatus.IDLE
)