package theme

import androidx.compose.runtime.Composable

@Composable
expect fun KvltTheme(
    isDarkTheme: Boolean,
    isDynamicColors: Boolean,
    content: @Composable () -> Unit
)