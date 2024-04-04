package com.alaskaair.uirepository.data.repository

import com.alaskaair.uirepository.data.remote.dto.DesignTokenResponse
import retrofit2.Response

/**
 * Interface representing the repository for design tokens.
 *
 * This repository is responsible for fetching design tokens from the server.
 * It abstracts the data source and provides a clean API to the rest of the application.
 */
interface DesignTokenRepository {

    /**
     * Fetches the design token from the server.
     *
     * This function makes a network request to the server and retrieves the design token.
     * The design token is a stylistic atomic unit, defined by a theme.
     *
     * @return A [Response] object containing a [DesignTokenResponse] or an error.
     */
    suspend fun getDesignToken(): Response<DesignTokenResponse>

}