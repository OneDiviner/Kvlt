package com.example.impl.presentation.expandedPlayer

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.api.Track

fun Long?.toTimeFormat(): String {
    val totalSeconds = this?.div(1000)
    val minutes = totalSeconds?.div(60)
    val seconds = totalSeconds?.rem(60)
    return "$minutes:${seconds.toString().padStart(2, '0')}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackProgressSlider(
    modifier: Modifier = Modifier,
    currentTrack: Track? = null,
    currentPosition: Long? = null,
    onSeek: (Long) -> Unit = {}
) {

    var sliderValue by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }

    LaunchedEffect(currentPosition) {
        if (!isDragging) {
            sliderValue = currentPosition?.toFloat() ?: 0f
        }
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            modifier = Modifier,
            text = (if (isDragging) sliderValue.toLong() else (currentPosition ?: 0L)).toTimeFormat(),
            fontSize = 12.sp
        )
        Slider(
            modifier = Modifier.weight(2f),
            value = sliderValue,
            onValueChange = { newValue ->
                isDragging = true
                sliderValue = newValue
            },
            onValueChangeFinished = {
                isDragging = false
                onSeek(sliderValue.toLong())
            },
            valueRange = 0f..(currentTrack?.duration?.toFloat()?.coerceAtLeast(1f) ?: 0f),
            thumb = { state ->
                SliderDefaults.Thumb(
                    modifier = Modifier.size(12.dp).offset(y = 2.dp),
                    interactionSource = MutableInteractionSource(),
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            },
            track = { state ->
                SliderDefaults.Track(
                    sliderState = state,
                    modifier = Modifier.height(3.dp).clip(RoundedCornerShape(6.dp)),
                    drawStopIndicator = null,
                    thumbTrackGapSize = 0.dp,
                    trackInsideCornerSize = 10.dp,
                    colors = SliderDefaults.colors(
                        activeTrackColor = MaterialTheme.colorScheme.onBackground,
                        inactiveTrackColor = MaterialTheme.colorScheme.onBackground.copy(0.3f),
                    ),
                )
            }
        )
        Text(
            modifier = Modifier,
            text = currentTrack?.duration?.toTimeFormat() ?: "",
            fontSize = 12.sp
        )
    }
}