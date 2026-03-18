package com.example.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.rememberHazeState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun GlassIconButton(
    modifier: Modifier = Modifier,
    hazeState: HazeState = rememberHazeState(),
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
            .size(40.dp)
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
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(icon),
            tint = MaterialTheme.colorScheme.onBackground.copy(0.85f),
            contentDescription = icon.toString(),
        )
    }
}