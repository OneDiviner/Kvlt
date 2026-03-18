package com.example.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.PlayerController
import com.example.api.Track
import com.example.impl.domain.TracksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class TracksViewModel(
    private val repository: TracksRepository,
    private val playerController: PlayerController
): ViewModel(), ContainerHost<TracksState, TracksSideEffect> {

    override val container = container<TracksState, TracksSideEffect>(initialState = TracksState())

    init {
        dispatch(TracksIntent.LoadTracks)
        dispatch(TracksIntent.ObservePlaybackState)
    }

    fun dispatch(intent: TracksIntent) {
        when(intent) {
            is TracksIntent.LoadTracks -> loadSongs()
            is TracksIntent.ObservePlaybackState -> observePlaybackState()
            is TracksIntent.Play -> play(intent.track)
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

    private fun play(track: Track) = intent {
        withContext(Dispatchers.Main) {
           playerController.play(track)
        }
    }

    private fun loadSongs() = intent {
        reduce { state.copy(isLoading = true) }

        try {
            val trackList = repository.loadTracks()

            reduce {
                state.copy(
                    trackList = trackList,
                    isLoading = false,
                    error = null
                )
            }

            postSideEffect(TracksSideEffect.ShowMessage("Loaded ${trackList.size} songs"))

        } catch (e: Exception) {
            reduce {
                state.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load songs"
                )
            }

            postSideEffect(TracksSideEffect.ShowError(e.message ?: "Unknown error"))
        }
    }
}