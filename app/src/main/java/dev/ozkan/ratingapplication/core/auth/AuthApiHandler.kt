package dev.ozkan.ratingapplication.core.auth

import dev.ozkan.ratingapplication.core.auth.dto.login.LoginData
import dev.ozkan.ratingapplication.core.auth.dto.login.LoginResponse
import dev.ozkan.ratingapplication.core.auth.dto.register.RegisterData
import dev.ozkan.ratingapplication.core.auth.dto.register.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthApiHandler {
    @PUT("/api/auth/login")
    fun loginRequest(@Body loginData: LoginData): Call<LoginResponse>

    @POST("/api/auth/register")
    fun registerRequest(@Body registerData : RegisterData): Call<RegisterResponse>

}