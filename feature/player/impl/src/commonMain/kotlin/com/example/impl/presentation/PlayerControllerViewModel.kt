package com.example.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.PlayerController
import com.example.api.PlayerIntent
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import org.orbitmvi.orbit.ContainerHost

class PlayerControllerViewModel(
    private val playerController: PlayerController
): ViewModel(), ContainerHost<PlayerControllerState, Nothing> {

    override val container = container<PlayerControllerState, Nothing>(initialState = PlayerControllerState())

    init {
        dispatch(PlayerControllerIntent.ObservePlaybackState)
    }

    fun dispatch(intent: PlayerControllerIntent) {
        when(intent) {
            is PlayerControllerIntent.TogglePlayPause -> togglePlayPause()
            is PlayerControllerIntent.NextTrack -> nextTrack()
            is PlayerControllerIntent.PreviousTrack -> previousTrack()
            is PlayerControllerIntent.SeekTo -> seekTo(intent.position)
            is PlayerControllerIntent.ObservePlaybackState -> observePlaybackState()
        }
    }

    private fun observePlaybackState() = intent {
        viewModelScope.launch {
            println("Position is ${state.playbackState.currentPosition}")
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
        viewModelScope.launch {
            if (state.playbackState.isPlaying) {
                playerController.handleIntent(PlayerIntent.Pause)
            } else {
                playerController.handleIntent(PlayerIntent.Resume)
            }
        }
    }

    private fun nextTrack() = intent {
        viewModelScope.launch {
            playerController.handleIntent(PlayerIntent.Next)
        }
    }

    private fun previousTrack() = intent {
        viewModelScope.launch {
            playerController.handleIntent(PlayerIntent.Previous)
        }
    }

    private fun seekTo(position: Long) = intent {
        viewModelScope.launch {
            playerController.handleIntent(PlayerIntent.SeekTo(position))
        }
    }

}