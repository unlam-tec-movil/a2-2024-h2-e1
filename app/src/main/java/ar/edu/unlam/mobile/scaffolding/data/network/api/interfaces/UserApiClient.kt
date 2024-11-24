package ar.edu.unlam.mobile.scaffolding.data.network.api.interfaces

import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.ProfileResponseDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.UpdateProfileBodyDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT

interface UserApiClient {
    @Headers("Application-Token: 2ebc5616c137d3228527ef06ca7230684673761fa2fb2ff5adc96cb01e53ccbc")
    @GET("me/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String,
    ): ProfileResponseDto

    @Headers("Application-Token: 2ebc5616c137d3228527ef06ca7230684673761fa2fb2ff5adc96cb01e53ccbc")
    @PUT("me/profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body body: UpdateProfileBodyDto,
    ): ProfileResponseDto
}
