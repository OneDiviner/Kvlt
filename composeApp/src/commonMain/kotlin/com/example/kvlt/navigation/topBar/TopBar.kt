package com.example.kvlt.navigation.topBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.ui.GlassIconButton
import com.example.ui.GlassSearchTextField
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import kvlt.core.resources.generated.resources.Res
import kvlt.core.resources.generated.resources.logo_icon
import kvlt.core.resources.generated.resources.settings_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun TopBar(
    onScroll: Boolean,
    hazeState: HazeState = rememberHazeState()
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .width(50.dp)
                    .height(36.dp),
                painter = painterResource(Res.drawable.logo_icon),
                contentDescription = "logo_icon",
                tint = MaterialTheme.colorScheme.onBackground
            )
            GlassIconButton(
                modifier = Modifier,
                icon = Res.drawable.settings_icon,
                hazeState = hazeState,
                onClick = {

                }
            )
        }
        AnimatedVisibility(
            visible = !onScroll,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .zIndex(3f)
                            .size(80.dp)
                            .rotate(18f)
                            .offset(y = 5.dp)
                            .dropShadow(
                                shape = RoundedCornerShape(12.dp),
                                shadow = Shadow(
                                    radius = 5.dp,
                                    color = Color(0xFF050505),
                                    spread = 5.dp,
                                    alpha = 0.15f
                                )
                            ),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Blue
                        )
                    ) {

                    }
                    Card(
                        modifier = Modifier
                            .zIndex(2f)
                            .size(80.dp)
                            .rotate(-14f)
                            .offset(y = -(3).dp, x = (20).dp)
                            .dropShadow(
                                shape = RoundedCornerShape(12.dp),
                                shadow = Shadow(
                                    radius = 5.dp,
                                    color = Color(0xFF050505),
                                    spread = 5.dp,
                                    alpha = 0.15f
                                )
                            ),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Red
                        )
                    ) {

                    }
                    Card(
                        modifier = Modifier
                            .zIndex(1f)
                            .size(80.dp)
                            .rotate(14f)
                            .offset(y = (-5).dp, x = (-20).dp)
                            .dropShadow(
                                shape = RoundedCornerShape(12.dp),
                                shadow = Shadow(
                                    radius = 5.dp,
                                    color = Color(0xFF050505),
                                    spread = 5.dp,
                                    alpha = 0.15f
                                )
                            ),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Green
                        )
                    ) {

                    }
                }
                Text(
                    modifier = Modifier,
                    text = "Треки с устройства",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 20.sp,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    modifier = Modifier,
                    text = "564 трека",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                        fontSize = 16.sp,
                        letterSpacing = 0.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }

        var value by remember { mutableStateOf("") }

        GlassSearchTextField(
            modifier = Modifier,
            hazeState = hazeState,
            value = value,
            onValueChange = { value = it}
        )

        /*LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(8) {
                FilterChip(
                    modifier = Modifier
                        .hazeSource(hazeState)
                        .hazeEffect(hazeState) {
                            blurEnabled = true
                            blurRadius = 50.dp
                        }
                        .dropShadow(
                            shape = RoundedCornerShape(16.dp),
                            shadow = Shadow(
                                radius = 20.dp,
                                color = Color(0xFF050505),
                                spread = 1.dp,
                                alpha = 0.15f
                            )
                        ),
                    selected = false,
                    label = {
                        Text(
                            text = "Поиск",
                            color = MaterialTheme.colorScheme.onBackground.copy(0.85f),
                            fontSize = 12.sp
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.onBackground.copy(0.05f),
                        selectedContainerColor = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                        disabledSelectedContainerColor = MaterialTheme.colorScheme.onBackground.copy(0.05f)
                    ),
                    border = null,
                    onClick = {  },
                )
            }
        }*/
    }
}