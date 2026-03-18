package com.example.impl.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun TracksView(
    scrollState: LazyListState,
    viewModel: TracksViewModel = koinViewModel<TracksViewModel>()
) {

    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is TracksSideEffect.ShowMessage -> {

            }
            is TracksSideEffect.ShowError -> {

            }
        }
    }

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        state.error != null -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text("Возникла ошибка", modifier = Modifier.align(Alignment.Center))
            }
        }

        state.trackList.isNotEmpty() -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
                state = scrollState
            ) {
                items(state.trackList, key = { it.id }) { track ->
                    TrackView(
                        track = track,
                        isCurrentTrack = track.id == state.playbackState.currentTrack?.id,
                        onClick = { viewModel.dispatch(TracksIntent.Play(track)) }
                    )
                }
            }
        }
    }
}