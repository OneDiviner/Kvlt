package com.example.kvlt

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.kvlt.di.initKoin
import theme.KvltTheme

fun main() = application {

    initKoin {
        printLogger()
    }

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