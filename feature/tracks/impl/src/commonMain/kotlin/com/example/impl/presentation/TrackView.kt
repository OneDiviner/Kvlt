package com.example.impl.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.api.Track
import kvlt.core.resources.generated.resources.Res
import kvlt.core.resources.generated.resources.album_icon
import org.jetbrains.compose.resources.painterResource

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
fun TrackView(
    modifier: Modifier = Modifier,
    track: Track,
    isCurrentTrack: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isCurrentTrack) MaterialTheme.colorScheme.onBackground.copy(0.15f) else Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            AlbumArtView(albumArtUri = track.albumArtUri)

            Column(
                modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = track.title ?: "",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 16.sp,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    modifier = Modifier,
                    text = track.artist ?: "",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                        fontSize = 14.sp,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }
    }
}