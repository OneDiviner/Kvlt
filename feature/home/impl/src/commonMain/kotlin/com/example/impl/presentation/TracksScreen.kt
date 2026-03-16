package com.example.impl.presentation

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impl.data.Track
import com.example.impl.domain.TracksRepository
import org.koin.compose.koinInject

@Composable
fun TracksView(
    scrollState: LazyListState
) {
    val repository: TracksRepository = koinInject()
    var tracks by remember { mutableStateOf<List<Track>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            tracks = repository.loadTracks()
            println("✅ Загружено треков: ${tracks.size}")  // Лог в консоль
            tracks.forEach { track ->
                println("   - ${track.title} (${track.artist})")
            }
        } catch (e: Exception) {
            println("❌ Ошибка: ${e.message}")
        }
    }

    LazyColumn(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(vertical = 12.dp),
        state = scrollState
    ) {
        items(tracks) { track ->
            TrackView(track)
        }
    }

}

@Composable
fun TrackView(
    track: Track
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {

        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier.size(48.dp).background(color = MaterialTheme.colorScheme.onBackground.copy(0.3f), shape = RoundedCornerShape(6.dp))
            )
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