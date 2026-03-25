package com.example.impl.presentation.expandedPlayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.GlassIconButton
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.rememberHazeState
import kvlt.core.resources.generated.resources.Res
import kvlt.core.resources.generated.resources.arrow_down_icon
import kvlt.core.resources.generated.resources.more_icon

@Composable
fun ExpandedPlayerTopBar(
    modifier: Modifier = Modifier,
    hazeState: HazeState = rememberHazeState(),
    onDismissButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GlassIconButton(
                hazeState = hazeState,
                icon = Res.drawable.arrow_down_icon,
                onClick = onDismissButtonClick
            )
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.onBackground.copy(0.15f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .dropShadow(
                        shape = RoundedCornerShape(16.dp),
                        shadow = Shadow(
                            radius = 20.dp,
                            color = Color(0xFF050505),
                            spread = 1.dp,
                            alpha = 0.15f,
                        )
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .hazeEffect(hazeState) {
                        blurEnabled = true
                        blurRadius = 200.dp
                        noiseFactor = 0.05f
                    }
            ) {
                Row {
                    FilterChip(
                        selected = true,
                        label = {
                            Text( //TODO: Apply textStyle
                                modifier = Modifier.fillMaxWidth(),
                                text = "Обложка", //TODO: To string resources
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onBackground.copy(0.85f),
                                    fontSize = 14.sp,
                                    letterSpacing = 0.sp,
                                    fontWeight = FontWeight.Light,
                                    textAlign = TextAlign.Center
                                ),
                            )
                        },
                        onClick = {},
                        modifier = Modifier.padding(0.dp).width(100.dp).height(35.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.Transparent,
                            selectedContainerColor = MaterialTheme.colorScheme.onBackground.copy(0.15f)
                        ),
                        border = null
                    )
                    FilterChip(
                        selected = false,
                        label = {
                            Text( //TODO: Apply textStyle
                                modifier = Modifier.fillMaxWidth(),
                                text = "Текст", //TODO: To string resources
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onBackground.copy(0.85f),
                                    fontSize = 14.sp,
                                    letterSpacing = 0.sp,
                                    fontWeight = FontWeight.Light,
                                    textAlign = TextAlign.Center
                                ),
                            )
                        },
                        onClick = {},
                        modifier = Modifier.padding(0.dp).width(100.dp).height(35.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.Transparent,
                            selectedContainerColor = MaterialTheme.colorScheme.onBackground.copy(0.15f)
                        ),
                        border = null
                    )
                }
            }
            GlassIconButton(
                hazeState = hazeState,
                icon = Res.drawable.more_icon,
                onClick = {}
            )
        }
        Text(
            modifier = Modifier,
            text = "Треки с устройства",
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground.copy(0.85f),
                fontSize = 14.sp,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Light
            ),
        )
    }
}