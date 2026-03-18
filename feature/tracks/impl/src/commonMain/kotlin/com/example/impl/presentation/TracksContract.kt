package com.example.impl.presentation

import com.example.api.PlaybackState
import com.example.api.Track
import kotlinx.serialization.Serializable

@Serializable
data class TracksState(
    val trackList: List<Track> = emptyList(),
    val playbackState: PlaybackState = PlaybackState(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed class TracksSideEffect {
    data class ShowMessage(val message: String) : TracksSideEffect()
    data class ShowError(val error: String) : TracksSideEffect()
}

sealed class TracksIntent {
    object LoadTracks : TracksIntent()
    object ObservePlaybackState : TracksIntent()
    data class Play(val track: Track) : TracksIntent()
}