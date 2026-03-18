package com.example.kvlt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import coil3.compose.AsyncImage
import com.example.api.TracksNavKey
import com.example.impl.presentation.TracksView
import com.example.kvlt.navigation.bottomBar.BottomBar
import com.example.kvlt.navigation.topBar.TopBar
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState

private const val SLIPKNOT_ALBUM_ART_URL = "https://i.ebayimg.com/images/g/g6wAAeSwh8NoNFSn/s-l1600.jpg"
private const val GRIMA_ALBUM_ART_URL = "https://avatars.mds.yandex.net/i?id=d38375998f727662cc24a654f0d6fd47_l-3691447-images-thumbs&n=13"

@Composable
fun KvltApp() {
    MaterialTheme {

        val scrollState = rememberLazyListState()

        val maxTopBarOffset = 500f
        var topBarOffset by remember { mutableFloatStateOf(maxTopBarOffset) }

        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    val delta = available.y
                    if (delta < 0 && topBarOffset > 0f) {
                        val newOffset = (topBarOffset + delta).coerceIn(0f, maxTopBarOffset)
                        topBarOffset = newOffset
                        return Offset(0f, delta)
                    }
                    val isListAtTop = scrollState.firstVisibleItemIndex == 0 &&
                            scrollState.firstVisibleItemScrollOffset == 0

                    if (delta > 0 && isListAtTop && topBarOffset < maxTopBarOffset) {
                        val newOffset = (topBarOffset + delta).coerceIn(0f, maxTopBarOffset)
                        topBarOffset = newOffset
                        return Offset(0f, delta)
                    }

                    return Offset.Zero
                }
            }
        }

        val scrollAlpha = topBarOffset / maxTopBarOffset

        val backStack = remember { mutableStateListOf<Any>(TracksNavKey) }

        val hazeState = rememberHazeState()

        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
        ) {
            //TODO: Load image of first track of Screen
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(200.dp)
                    .hazeSource(hazeState, zIndex = 0f),
                model = SLIPKNOT_ALBUM_ART_URL,
                contentDescription = "album_background",
                contentScale = ContentScale.Crop,
            )
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(nestedScrollConnection)
                    .zIndex(1f),
                containerColor = Color.Transparent,
                topBar = { TopBar(scrollAlpha, hazeState) },
                bottomBar = { BottomBar() }
            ) { paddingValues ->

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                        )
                ) {
                    NavDisplay(
                        backStack = backStack,
                        onBack = {
                            backStack.removeLastOrNull()
                        },
                        entryProvider = entryProvider {
                            entry<TracksNavKey> {
                                TracksView(scrollState)
                            }
                        }
                    )
                }
            }
        }
    }
}