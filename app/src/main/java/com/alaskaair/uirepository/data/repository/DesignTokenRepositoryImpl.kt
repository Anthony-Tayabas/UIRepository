package com.alaskaair.uirepository.data.repository

import com.alaskaair.uirepository.data.remote.api.DesignTokenApi
import com.alaskaair.uirepository.data.remote.dto.DesignTokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

/**
 * Implementation of the [DesignTokenRepository] interface.
 *
 * This class is responsible for fetching design tokens from the server.
 * It uses the [DesignTokenApi] to make the actual network request.
 *
 * @property designTokenApi The API used to fetch design tokens from the server.
 */
class DesignTokenRepositoryImpl @Inject constructor(
    private val designTokenApi: DesignTokenApi
) : DesignTokenRepository {

    /**
     * Fetches the design token from the server.
     *
     * This function makes a network request to the server and retrieves the design token.
     * The design token is a stylistic atomic unit, defined by a theme.
     * The network request is made on the IO dispatcher to offload the main thread.
     *
     * @return A [Response] object containing a [DesignTokenResponse] or an error.
     */
    override suspend fun getDesignToken(): Response<DesignTokenResponse> {
        return withContext(Dispatchers.IO) {
            designTokenApi.getDesignToken()
        }
    }
}