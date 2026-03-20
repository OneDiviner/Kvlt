package com.example.impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExpandedPlayerView(
    viewModel: PlayerViewModel = koinViewModel<PlayerViewModel>(),
    modifier: Modifier = Modifier
) {

    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()
    val hazeState = rememberHazeState()


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .blur(200.dp)
                .hazeSource(hazeState, zIndex = 0f),
            model = state.playbackState.currentTrack?.albumArtUri,
            contentDescription = "album_background",
            contentScale = ContentScale.Crop,
        )
        Column(

        ) {

        }
    }
}