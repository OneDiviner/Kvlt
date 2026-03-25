package com.example.impl.presentation

import com.example.api.PlaybackState
import kotlinx.serialization.Serializable

@Serializable
data class PlayerControllerState(
    val playbackState: PlaybackState = PlaybackState(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed class PlayerControllerIntent {
    object TogglePlayPause : PlayerControllerIntent()
    object NextTrack : PlayerControllerIntent()
    object PreviousTrack : PlayerControllerIntent()
    data class SeekTo(val position: Long) : PlayerControllerIntent()
    object ObservePlaybackState : PlayerControllerIntent()
}