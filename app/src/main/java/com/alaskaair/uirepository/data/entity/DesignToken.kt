package com.alaskaair.uirepository.data.entity

/**
 * Data class representing a design token in the application.
 *
 * A design token is a stylistic atomic unit, defined by a theme. In this case, it represents
 * different color values for both dark and light themes.
 *
 * @property darkOnPrimary The color value for text or UI elements on a primary surface in dark theme.
 * @property darkPrimary The primary color value in dark theme.
 * @property lightOnPrimary The color value for text or UI elements on a primary surface in light theme.
 * @property lightPrimary The primary color value in light theme.
 */
data class DesignToken(
    val darkOnPrimary: Long,
    val darkPrimary: Long,
    val lightOnPrimary: Long,
    val lightPrimary: Long
)