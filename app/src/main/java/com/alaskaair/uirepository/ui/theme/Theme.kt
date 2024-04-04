package com.alaskaair.uirepository.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.alaskaair.uirepository.data.entity.DesignToken

/**
 * Dark color scheme for the application.
 */
private var DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

/**
 * Light color scheme for the application.
 */
private var LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

/**
 * Updates the color scheme with the provided design token.
 *
 * @param designToken The design token used to update the color scheme.
 */
private fun updateColorScheme(designToken: DesignToken) {
    DarkColorScheme = darkColorScheme(
        primary =  Color(designToken.darkPrimary),
        secondary = Color(designToken.darkOnPrimary),
    )

    LightColorScheme = lightColorScheme(
        primary = Color(designToken.lightPrimary),
        secondary = Color(designToken.lightOnPrimary),
    )
}

/**
 * Composable function to apply the theme for the application.
 *
 * This function applies the theme for the application based on the provided parameters.
 * It updates the color scheme with the provided design token and applies the dark or light theme based on the system settings.
 *
 * @param darkTheme A boolean indicating whether to apply the dark theme. Default is based on the system settings.
 * @param designToken The design token used to update the color scheme. Default is null.
 * @param content The content to be themed.
 */
@Composable
fun UIRepositoryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    designToken: DesignToken? = null,
    content: @Composable () -> Unit
) {

    designToken?.let {
        updateColorScheme(it)
    }

    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}