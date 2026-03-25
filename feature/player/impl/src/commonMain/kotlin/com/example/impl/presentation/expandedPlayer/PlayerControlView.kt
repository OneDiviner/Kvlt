package com.example.impl.presentation.expandedPlayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.rememberHazeState
import kvlt.core.resources.generated.resources.Res
import kvlt.core.resources.generated.resources.backward_icon
import kvlt.core.resources.generated.resources.forward_icon
import kvlt.core.resources.generated.resources.pause_icon
import kvlt.core.resources.generated.resources.play_icon
import kvlt.core.resources.generated.resources.repeat_mode_on_icon
import kvlt.core.resources.generated.resources.shuffle_mode_icon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PlayerControlButton(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    onClick: () -> Unit
) {
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
            .size(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground.copy(0.85f)
        ),
        shape = CircleShape,
        contentPadding = PaddingValues(4.dp),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(icon),
            tint = MaterialTheme.colorScheme.onBackground.copy(0.85f),
            contentDescription = icon.toString(),
        )
    }
}

@Composable // TODO: Covered by BottomBar
fun PlayerControlView(
    modifier: Modifier = Modifier,
    isPlaying: Boolean = false,
    onPreviousClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onPlayPauseClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerControlButton(
            modifier = Modifier,
            icon = Res.drawable.repeat_mode_on_icon,
            onClick = {}
        )
        PlayerControlButton(
            modifier = Modifier.size(48.dp),
            icon = Res.drawable.backward_icon,
            onClick = onPreviousClick
        )
        PlayerControlButton(
            modifier = Modifier.size(60.dp),
            icon = if (isPlaying) Res.drawable.pause_icon
            else Res.drawable.play_icon,
            onClick = onPlayPauseClick
        )
        PlayerControlButton(
            modifier = Modifier.size(48.dp),
            icon = Res.drawable.forward_icon,
            onClick = onNextClick
        )
        PlayerControlButton(
            modifier = Modifier,
            icon = Res.drawable.shuffle_mode_icon,
            onClick = {}
        )
    }
}