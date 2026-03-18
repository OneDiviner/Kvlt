package com.example.impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.api.PlaybackStatus
import kvlt.core.resources.generated.resources.Res
import kvlt.core.resources.generated.resources.album_icon
import kvlt.core.resources.generated.resources.play_icon
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AlbumArtView(
    modifier: Modifier = Modifier,
    albumArtUri: String? = null
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(6.dp)
            )
            .clip(RoundedCornerShape(6.dp)),
    ) {
        if (albumArtUri.isNullOrEmpty()) {
            Icon(
                modifier = Modifier.padding(8.dp),
                painter = painterResource(Res.drawable.album_icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(0.85f)
            )
        } else {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = albumArtUri,
                contentDescription = "album_art"
            )
        }
    }
}

@Composable
fun CollapsedPlayerView(
    viewModel: PlayerViewModel = koinViewModel<PlayerViewModel>()
) {

    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        onClick = {}
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            AlbumArtView(albumArtUri = state.playbackState.currentTrack?.albumArtUri)

            Column(
                modifier = Modifier.padding(8.dp).weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = state.playbackState.currentTrack?.title ?: "",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    modifier = Modifier,
                    text = state.playbackState.currentTrack?.artist ?: "",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                        fontSize = 14.sp,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }
            IconButton(
                modifier = Modifier,
                shape = CircleShape,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ),
                onClick = {  }
            ) {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(Res.drawable.play_icon),
                    contentDescription = null
                )
            }
        }
    }

}