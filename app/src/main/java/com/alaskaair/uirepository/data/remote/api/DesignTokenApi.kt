package com.alaskaair.uirepository.data.remote.api

import com.alaskaair.uirepository.data.remote.dto.DesignTokenResponse
import retrofit2.Response
import retrofit2.http.GET

interface DesignTokenApi {

    /**
     * Fetches the design token from the server.
     *
     * This function makes a GET request to the server and retrieves the design token.
     * The design token is a stylistic atomic unit, defined by a theme.
     *
     * @return A [Response] object containing a [DesignTokenResponse] or an error.
     */

    /**
     * This POC is using https://jsonbin.io/ as a mock server to fetch the design token.
     * The endpoint below may be expired when you try this POC.
     * You can create a new bin in https://jsonbin.io/ and replace the endpoint below with your new bin.
     * Here's is the template of the JSON response recognized by the POC:
     * {
     *   "light_primary": "FF00639B",
     *   "light_on_primary": "FFFFFFFF",
     *   "dark_primary": "FF00416D",
     *   "dark_on_primary": "FF3D85C6"
     * }
     */

    @GET("/v3/qs/660e1d1ce41b4d34e4deeb95") // Auro Classic
//    @GET("/v3/qs/660dcc54ad19ca34f85487bf") // Auro Hawaiian
    suspend fun getDesignToken(): Response<DesignTokenResponse>

}