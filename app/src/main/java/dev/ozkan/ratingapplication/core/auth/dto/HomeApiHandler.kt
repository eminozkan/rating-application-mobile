package dev.ozkan.ratingapplication.core.auth.dto

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeApiHandler {

    @GET("/api/me")
    fun getSession(@Header ("Authorization") bearerToken : String) : Call<SessionResponse>
}