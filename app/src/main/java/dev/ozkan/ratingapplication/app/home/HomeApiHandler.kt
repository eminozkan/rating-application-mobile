package dev.ozkan.ratingapplication.app.home

import dev.ozkan.ratingapplication.app.home.dto.SessionResponse
import okhttp3.Cookie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeApiHandler {

    @GET("/api/me")
    fun getSession(@Header ("Authorization") bearerToken : String) : Call<SessionResponse>
}