package com.example.kvlt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import theme.KvltTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            KvltTheme(
                isDarkTheme = isSystemInDarkTheme(),
                isDynamicColors = false
            ) {
                KvltApp()
            }
        }
    }
}