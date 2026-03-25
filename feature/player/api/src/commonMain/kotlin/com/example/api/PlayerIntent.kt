package com.example.api

sealed interface PlayerIntent {
    data class Play(val track: Track): PlayerIntent
    data object Pause: PlayerIntent
    data object Resume: PlayerIntent
    data object Next: PlayerIntent
    data object Previous: PlayerIntent
    data class SeekTo(val position: Long): PlayerIntent
}