package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import kvlt.core.resources.generated.resources.Res
import kvlt.core.resources.generated.resources.search_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun GlassSearchTextField(
    modifier: Modifier = Modifier,
    hazeState: HazeState = rememberHazeState(),
    value: String,
    onValueChange: (String) -> Unit
) {

    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    BasicTextField(
        modifier = modifier
            .dropShadow(
                shape = RoundedCornerShape(16.dp),
                shadow = Shadow(
                    radius = 20.dp,
                    color = Color(0xFF050505),
                    spread = 1.dp,
                    alpha = 0.15f,
                )
            )
            .onFocusChanged {
                isFocused = it.isFocused
                if (!it.isFocused) {
                    focusManager.clearFocus()
                }
            }
            .clip(RoundedCornerShape(16.dp))
            .height(42.dp)
            .hazeEffect(hazeState) {
                blurEnabled = true
                blurRadius = 200.dp
                noiseFactor = 0.05f
            },
        value = value,
        onValueChange = onValueChange,
        enabled = true,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
            }
        ),
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onBackground.copy(0.85f),
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.onBackground.copy(0.15f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.search_icon),
                    contentDescription = "search_icon",
                    modifier = Modifier.size(18.dp),
                    tint = MaterialTheme.colorScheme.onBackground.copy(0.85f)
                )
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier,
                            text = "Поиск...", //TODO: To text resources
                            style = TextStyle(
                                color = if (isFocused) MaterialTheme.colorScheme.onBackground.copy(0.3f) else MaterialTheme.colorScheme.onBackground.copy(0.5f),
                                fontSize = 14.sp,
                                letterSpacing = 0.sp,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 20.sp
                            )
                        )
                    }
                }
            }
        }
    )
}