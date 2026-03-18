package com.example.api

import kotlinx.serialization.Serializable

@Serializable
enum class PlaybackStatus {
    IDLE,
    BUFFERING,
    READY,
    ENDED,
    ERROR
}