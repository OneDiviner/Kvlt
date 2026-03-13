package com.example.kvlt

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import theme.KvltTheme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kvlt",
    ) {
        KvltTheme(
            isDarkTheme = isSystemInDarkTheme(),
            isDynamicColors = false
        ) {
            KvltApp()
        }
    }
}