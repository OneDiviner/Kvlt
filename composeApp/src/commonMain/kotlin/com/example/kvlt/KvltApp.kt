package com.example.kvlt

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.impl.presentation.TrackView
import com.example.impl.presentation.TracksView
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import kvlt.composeapp.generated.resources.Res
import kvlt.composeapp.generated.resources.collection_icon
import kvlt.composeapp.generated.resources.history_icon
import kvlt.composeapp.generated.resources.home_icon
import kvlt.composeapp.generated.resources.logo_icon
import kvlt.composeapp.generated.resources.search_icon
import kvlt.composeapp.generated.resources.settings_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun KvltApp() {
    MaterialTheme {

        val scrollState = rememberLazyListState()

        val isTopBarHidden by remember {
            derivedStateOf { scrollState.canScrollBackward }
        } //TODO: Add observe scroll, to hide/show top bar and resize elements

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.surface, //TODO: Load image of first track of Screen
            topBar = {
                TopBar(
                    isTopBarHidden
                )
            },
            bottomBar = {
                BottomBar()
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                TracksView(scrollState)
            }
        }
    }
}

@Composable
fun TopBar(
    onScroll: Boolean
) {

    val hazeState = rememberHazeState()

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 12.dp, vertical = 12.dp),
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
                contentDescription = "logo_icon"
            )
            IconButton(
                modifier = Modifier
                    .hazeSource(hazeState)
                    .hazeEffect(hazeState) {
                        blurEnabled = true
                        blurRadius = 50.dp
                    }
                    .dropShadow(
                        shape = CircleShape,
                        shadow = Shadow(
                            radius = 20.dp,
                            color = Color(0xFF050505),
                            spread = 1.dp,
                            alpha = 0.15f
                        )
                    ),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.onBackground.copy(0.05f),
                    contentColor = MaterialTheme.colorScheme.onBackground.copy(0.85f)
                ),
                shape = CircleShape,
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(Res.drawable.settings_icon),
                    contentDescription = "settings_icon_button"
                )
            }
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
        BasicTextField(
            value = "",
            onValueChange = {  },
            modifier = Modifier
                .height(42.dp)
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
            enabled = true,
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                lineHeight = 20.sp
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.onBackground.copy(0.05f), shape = RoundedCornerShape(16.dp))
                        .fillMaxHeight()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Leading Icon
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.search_icon),
                            contentDescription = "search_icon",
                            modifier = Modifier.size(18.dp),
                            tint = MaterialTheme.colorScheme.onBackground.copy(0.85f)
                        )
                    }
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "Поиск",
                            color = MaterialTheme.colorScheme.onBackground.copy(0.4f),
                            fontSize = 14.sp
                        )
                        innerTextField()
                    }
                }
            }
        )
        LazyRow(
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
        }
    }
}

@Composable
fun BottomBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = {

                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(Res.drawable.home_icon),
                        contentDescription = "home_icon",
                    )
                    Text(
                        modifier = Modifier,
                        text = "Главная",
                        style = TextStyle(
                            fontSize = 12.sp,
                            letterSpacing = 0.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {

                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(Res.drawable.collection_icon),
                        contentDescription = "collection_icon",
                    )
                    Text(
                        modifier = Modifier,
                        text = "Коллекция",
                        style = TextStyle(
                            fontSize = 12.sp,
                            letterSpacing = 0.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {

                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(Res.drawable.history_icon),
                        contentDescription = "history_icon",
                    )
                    Text(
                        modifier = Modifier,
                        text = "История",
                        style = TextStyle(
                            fontSize = 12.sp,
                            letterSpacing = 0.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}