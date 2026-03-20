package com.example.impl.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.impl.presentation.util.getWindowHeight
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: PlayerViewModel = koinViewModel<PlayerViewModel>(),
    onSheetHeightChanged: (alpha: Float) -> Unit = {},
    topBar: @Composable (() -> Unit)? = null,
    content: @Composable ((PaddingValues) -> Unit)
) {

    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    )
    val screenHeight = getWindowHeight() - 400f //TODO: Measure correct size of bottom bar
    val scope = rememberCoroutineScope()
    val alphaBySheetOffset by remember {
        derivedStateOf {
            val alpha = try {
                val offset = scaffoldState.bottomSheetState.requireOffset()
                ((offset) / screenHeight).coerceIn(0f, 1f)
            } catch (e: Exception) {
                1f
            }
            onSheetHeightChanged(alpha)
            alpha
        }
    }

    LaunchedEffect(state.playbackState.currentTrack) {
        if (state.playbackState.currentTrack != null &&
            scaffoldState.bottomSheetState.currentValue == SheetValue.Hidden) {
            scaffoldState.bottomSheetState.partialExpand()
        }
    }

    BottomSheetScaffold(
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned { size ->
                val height = size.size.height
                println("Height: $height")
            },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 140.dp,
        sheetDragHandle = null,
        sheetShape = RectangleShape,
        containerColor = Color.Transparent,
        sheetContainerColor = MaterialTheme.colorScheme.background,
        topBar = topBar,
        sheetContent = {
            Box(
                modifier = Modifier
            ) {
                ExpandedPlayerView(
                    modifier = Modifier.graphicsLayer { alpha = 1f - alphaBySheetOffset }
                )
                CollapsedPlayerView(
                    modifier = Modifier.graphicsLayer { alpha = alphaBySheetOffset },
                    onClick = {
                        scope.launch { scaffoldState.bottomSheetState.expand() }
                    }
                )
            }
        },
        content = content
    )
}