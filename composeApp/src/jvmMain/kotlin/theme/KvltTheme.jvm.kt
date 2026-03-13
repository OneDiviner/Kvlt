package theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import theme.dark.DarkColorScheme
import theme.light.LightColorScheme

@Composable
actual fun KvltTheme(
    isDarkTheme: Boolean,
    isDynamicColors: Boolean,
    content: @Composable (() -> Unit)
) {
    MaterialTheme(
        colorScheme = if(isDarkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}