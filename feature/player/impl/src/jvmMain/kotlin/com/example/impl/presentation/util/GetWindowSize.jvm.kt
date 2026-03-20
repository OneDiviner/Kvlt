package com.example.impl.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalWindowInfo

@Composable
actual fun getWindowHeight(): Float {
    val windowInfo = LocalWindowInfo.current
    return windowInfo.containerSize.height.toFloat()
}

@Composable
actual fun getWindowWidth(): Float {
    val windowInfo = LocalWindowInfo.current
    return windowInfo.containerSize.width.toFloat()
}