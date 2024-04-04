package com.alaskaair.uirepository.data.remote.dto

import com.alaskaair.uirepository.data.entity.DesignToken
import com.google.gson.annotations.SerializedName
import com.alaskaair.thememanager.data.DesignToken as DesignTokenForThemeManager

/**
 * Data class representing the response from the server when fetching a design token.
 *
 * @property id The unique identifier of the design token.
 * @property metadata Metadata about the design token, including creation time and read count.
 * @property record The actual design token record, containing color values.
 */
data class DesignTokenResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("record")
    val record: Record
) {
    /**
     * Nested data class representing the metadata of the design token.
     *
     * @property createdAt The creation time of the design token.
     * @property name The name of the design token.
     * @property readCountRemaining The remaining read count of the design token.
     * @property timeToExpire The time left before the design token expires.
     */
    data class Metadata(
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("readCountRemaining")
        val readCountRemaining: Int,
        @SerializedName("timeToExpire")
        val timeToExpire: Int
    )

    /**
     * Nested data class representing the actual design token record.
     *
     * @property darkOnPrimary The color value for text or UI elements on a primary surface in dark theme.
     * @property darkPrimary The primary color value in dark theme.
     * @property lightOnPrimary The color value for text or UI elements on a primary surface in light theme.
     * @property lightPrimary The primary color value in light theme.
     */
    data class Record(
        @SerializedName("dark_on_primary")
        val darkOnPrimary: String,
        @SerializedName("dark_primary")
        val darkPrimary: String,
        @SerializedName("light_on_primary")
        val lightOnPrimary: String,
        @SerializedName("light_primary")
        val lightPrimary: String
    )

    /**
     * Converts the design token record to a [DesignToken] object.
     *
     * @return A [DesignToken] object with color values converted from hexadecimal string to Long.
     */
    fun getDesignToken(): DesignToken {
        return DesignToken(
            darkOnPrimary = record.darkOnPrimary.toLong(radix = 16),
            darkPrimary = record.darkPrimary.toLong(radix = 16),
            lightOnPrimary = record.lightOnPrimary.toLong(radix = 16),
            lightPrimary = record.lightPrimary.toLong(radix = 16)
        )
    }

    /**
     * Converts the design token record to a [DesignTokenForThemeManager] object.
     *
     * @return A [DesignTokenForThemeManager] object with color values converted from hexadecimal string to Long.
     */
    fun getDesignTokenForThemeManager(): DesignTokenForThemeManager {
        return DesignTokenForThemeManager(
            darkOnPrimary = record.darkOnPrimary.toLong(radix = 16),
            darkPrimary = record.darkPrimary.toLong(radix = 16),
            lightOnPrimary = record.lightOnPrimary.toLong(radix = 16),
            lightPrimary = record.lightPrimary.toLong(radix = 16)
        )
    }
}