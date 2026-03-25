package com.example.impl.presentation.expandedPlayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.api.Track

@Composable
fun TrackInfoView(
    modifier: Modifier = Modifier,
    currentTrack: Track? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier.size(54.dp).clip(CircleShape),
            model = currentTrack?.albumArtUri,
            contentDescription = "Artist",
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                text = currentTrack?.title ?: ""
            )
            Text(
                modifier = Modifier,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                text = currentTrack?.artist ?: ""
            )
        }
    }
}