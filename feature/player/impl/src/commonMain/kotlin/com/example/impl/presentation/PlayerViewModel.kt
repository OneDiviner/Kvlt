package com.example.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.PlayerController
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import org.orbitmvi.orbit.ContainerHost

class PlayerViewModel(
    private val playerController: PlayerController
): ViewModel(), ContainerHost<PlayerState, Nothing> {

    override val container = container<PlayerState, Nothing>(initialState = PlayerState())

    init {
        dispatch(PlayerIntent.ObservePlaybackState)
    }

    fun dispatch(intent: PlayerIntent) {
        when(intent) {
            is PlayerIntent.TogglePlayPause -> {}
            is PlayerIntent.NextTrack -> {}
            is PlayerIntent.PreviousTrack -> {}
            is PlayerIntent.SeekTo -> {}
            is PlayerIntent.ObservePlaybackState -> observePlaybackState()
        }
    }

    private fun observePlaybackState() = intent {
        viewModelScope.launch {
            playerController.playbackState.collect { playbackState ->
                reduce {
                    state.copy(
                        playbackState = playbackState
                    )
                }
            }
        }
    }

    private fun togglePlayPause() = intent {

    }

    private fun nextTrack() = intent {

    }

    private fun previousTrack() = intent {

    }

    private fun seekTo(position: Long) = intent {

    }

}