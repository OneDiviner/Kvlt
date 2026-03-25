package com.example.impl.presentation.expandedPlayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.impl.presentation.PlayerControllerIntent
import com.example.impl.presentation.PlayerControllerViewModel
import com.example.ui.AlbumArtView
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import kvlt.core.resources.generated.resources.Res
import kvlt.core.resources.generated.resources.add_to_playlist_icon
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

//TODO: Covered by CollapsedPlayerView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandedPlayerView(
    viewModel: PlayerControllerViewModel = koinViewModel<PlayerControllerViewModel>(),
    modifier: Modifier = Modifier,
    onDismissButtonClick: () -> Unit
) {

    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()
    val hazeState = rememberHazeState()


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .hazeSource(hazeState, zIndex = 0f)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.85f)
                .blur(50.dp)
                .hazeSource(hazeState, zIndex = 1f),
            model = state.playbackState.currentTrack?.albumArtUri,
            contentDescription = "album_background",
            contentScale = ContentScale.FillHeight,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(vertical = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ExpandedPlayerTopBar(
                modifier = Modifier.padding(horizontal = 24.dp),
                hazeState = hazeState,
                onDismissButtonClick = onDismissButtonClick
            )
            AlbumArtView(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .dropShadow(
                        shape = RoundedCornerShape(20.dp),
                        shadow = Shadow(
                            radius = 30.dp,
                            color = Color(0xFF050505),
                            spread = 8.dp,
                            alpha = 0.3f,
                        )
                    ),
                albumArtUri = state.playbackState.currentTrack?.albumArtUri,
                shape = RoundedCornerShape(20.dp)
            )

            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                TrackInfoView(
                    modifier = Modifier,
                    currentTrack = state.playbackState.currentTrack
                )
                TrackProgressSlider(
                    modifier = Modifier,
                    currentTrack = state.playbackState.currentTrack,
                    currentPosition = state.playbackState.currentPosition,
                    onSeek = { viewModel.dispatch(PlayerControllerIntent.SeekTo(it)) }
                )
            }
            PlayerControlView(
                modifier = Modifier.padding(horizontal = 24.dp),
                isPlaying = state.playbackState.isPlaying,
                onPlayPauseClick = { viewModel.dispatch(PlayerControllerIntent.TogglePlayPause) },
                onNextClick = { viewModel.dispatch(PlayerControllerIntent.NextTrack) },
                onPreviousClick = { viewModel.dispatch(PlayerControllerIntent.PreviousTrack) },
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(horizontal = 24.dp)
            ) {
                items(5) {
                    Button(
                        modifier = modifier
                            .dropShadow(
                                shape = CircleShape,
                                shadow = Shadow(
                                    radius = 20.dp,
                                    color = Color(0xFF050505),
                                    spread = 1.dp,
                                    alpha = 0.15f,
                                )
                            )
                            .clip(CircleShape)
                            .hazeEffect(hazeState) {
                                blurEnabled = true
                                blurRadius = 200.dp
                                noiseFactor = 0.05f
                            },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onBackground.copy(0.15f),
                            contentColor = MaterialTheme.colorScheme.onBackground.copy(0.85f)
                        ),
                        shape = CircleShape,
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        onClick = {}
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(Res.drawable.add_to_playlist_icon),
                                tint = MaterialTheme.colorScheme.onBackground.copy(0.85f),
                                contentDescription = null,
                            )
                            Text( //TODO: Apply textStyle
                                modifier = Modifier,
                                text = "В плейлист", //TODO: To string resources
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onBackground.copy(0.85f),
                                    fontSize = 14.sp,
                                    letterSpacing = 0.sp,
                                    fontWeight = FontWeight.Light,
                                    textAlign = TextAlign.Center
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}