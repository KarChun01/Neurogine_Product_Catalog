package com.example.neuroginesproduct.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun NeuroginesProductTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            background = BgDark,
            surface = NavbarBgDark,
            primary = TextPrimaryDark,
            secondary = TextSecondaryDark,
            tertiary = IndicatorDark,
            onBackground = TextTitleDark,
            surfaceVariant = SurfaceVariantDark,
            outline = BorderDark
        )
    } else {
        lightColorScheme(
            background = BgLight,
            surface = NavbarBgLight,
            primary = TextPrimaryLight,
            secondary = TextSecondaryLight,
            tertiary = IndicatorLight,
            onBackground = TextTitleLight,
            surfaceVariant = SurfaceVariantLight,
            outline = BorderLight
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
