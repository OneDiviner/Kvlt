package com.example.impl.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.window.layout.WindowMetricsCalculator

@Composable
actual fun getWindowHeight(): Float {
    val context = LocalContext.current
    val metrics = WindowMetricsCalculator.getOrCreate()
        .computeCurrentWindowMetrics(context)
    return metrics.bounds.height().toFloat()
}


@Composable
actual fun getWindowWidth(): Float {
    val context = LocalContext.current
    val metrics = WindowMetricsCalculator.getOrCreate()
        .computeCurrentWindowMetrics(context)
    return metrics.bounds.width().toFloat()
}