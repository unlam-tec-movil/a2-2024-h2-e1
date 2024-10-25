package ar.edu.unlam.mobile.scaffolding.data.network.api

import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.LoginBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.LoginResponseDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.RegisterBodyDto
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotTwitterApiClient {
    @Headers("Application-Token: 2ebc5616c137d3228527ef06ca7230684673761fa2fb2ff5adc96cb01e53ccbc")
    @POST("login")
    suspend fun logInUser(
        @Body body: LoginBodyDto,
    ): LoginResponseDto

    @Headers("Application-Token: 2ebc5616c137d3228527ef06ca7230684673761fa2fb2ff5adc96cb01e53ccbc")
    @POST("users")
    suspend fun registerUser(
        @Body body: RegisterBodyDto,
    ): LoginResponseDto
}
