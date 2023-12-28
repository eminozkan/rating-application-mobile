package dev.ozkan.ratingapplication.core.retrofit

import dev.ozkan.ratingapplication.core.auth.AuthApiHandler
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthRetrofitInstance {

        val api : AuthApiHandler by lazy {
            Retrofit.Builder()
                .baseUrl("http://192.168.1.187:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthApiHandler::class.java)
        }
}