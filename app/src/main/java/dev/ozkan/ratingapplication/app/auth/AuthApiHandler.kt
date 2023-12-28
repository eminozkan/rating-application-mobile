package dev.ozkan.ratingapplication.app.auth

import dev.ozkan.ratingapplication.app.auth.dto.login.LoginData
import dev.ozkan.ratingapplication.app.auth.dto.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT

interface AuthApiHandler {
    @PUT("/api/auth/login")
    fun loginRequest(@Body loginData: LoginData): Call<LoginResponse>

}