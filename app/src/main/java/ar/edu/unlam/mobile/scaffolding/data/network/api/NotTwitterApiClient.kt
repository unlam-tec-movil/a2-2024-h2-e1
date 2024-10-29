package ar.edu.unlam.mobile.scaffolding.data.network.api

import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.LoginBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.NewPostBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.NewPostResponseDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.ProfileResponseDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.RegisterBodyDto
import ar.edu.unlam.mobile.scaffolding.data.network.api.dto.TokenResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotTwitterApiClient {
    @Headers("Application-Token: 2ebc5616c137d3228527ef06ca7230684673761fa2fb2ff5adc96cb01e53ccbc")
    @POST("login")
    suspend fun logInUser(
        @Body body: LoginBodyDto,
    ): TokenResponseDto

    @Headers("Application-Token: 2ebc5616c137d3228527ef06ca7230684673761fa2fb2ff5adc96cb01e53ccbc")
    @POST("users")
    suspend fun registerUser(
        @Body body: RegisterBodyDto,
    ): TokenResponseDto

    @Headers("Application-Token: 2ebc5616c137d3228527ef06ca7230684673761fa2fb2ff5adc96cb01e53ccbc")
    @GET("me/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String,
    ): ProfileResponseDto

    @Headers("Application-Token: 2ebc5616c137d3228527ef06ca7230684673761fa2fb2ff5adc96cb01e53ccbc")
    @POST("me/tuits")
    suspend fun postNotTweet(
        @Header("Authorization") token: String,
        @Body body: NewPostBodyDto,
    ): NewPostResponseDto

    /* @Headers("Application-Token: 2ebc5616c137d3228527ef06ca7230684673761fa2fb2ff5adc96cb01e53ccbc")
        @GET("me/feed?page=1")
        suspend fun getFeed(): ResponseBody

    @Headers("Application-Token: 2ebc5616c137d3228527ef06ca7230684673761fa2fb2ff5adc96cb01e53ccbc")
    @POST("me/tuits/:tuitID/likes")
    suspend fun addLike(): ResponseBody

    @Headers("Application-Token: 2ebc5616c137d3228527ef06ca7230684673761fa2fb2ff5adc96cb01e53ccbc")
    @DELETE("me/tuits/:tuitID/likes")
    suspend fun addLike(): ResponseBody
     */
}
