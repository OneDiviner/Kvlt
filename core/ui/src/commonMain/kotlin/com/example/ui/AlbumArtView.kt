package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kvlt.core.resources.generated.resources.Res
import kvlt.core.resources.generated.resources.album_icon
import org.jetbrains.compose.resources.painterResource

//TODO: usage in CollapsedPlayerView, TrackView, ExpandedPlayerView, TopBar
@Composable
fun AlbumArtView(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    albumArtUri: String? = null
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = shape
            )
            .clip(shape),
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