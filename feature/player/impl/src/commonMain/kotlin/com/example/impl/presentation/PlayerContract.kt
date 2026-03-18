package com.example.impl.presentation

import com.example.api.PlaybackState
import kotlinx.serialization.Serializable

@Serializable
data class PlayerState(
    val playbackState: PlaybackState = PlaybackState(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed class PlayerIntent {
    object TogglePlayPause : PlayerIntent()
    object NextTrack : PlayerIntent()
    object PreviousTrack : PlayerIntent()
    data class SeekTo(val position: Long) : PlayerIntent()
    object ObservePlaybackState : PlayerIntent()
}