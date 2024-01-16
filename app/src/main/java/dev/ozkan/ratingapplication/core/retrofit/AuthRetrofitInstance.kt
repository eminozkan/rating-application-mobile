package dev.ozkan.ratingapplication.core.retrofit

import dev.ozkan.ratingapplication.core.auth.AuthApiHandler
import dev.ozkan.ratingapplication.API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthRetrofitInstance {

        val api : AuthApiHandler by lazy {
            Retrofit.Builder()
                .baseUrl(API.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthApiHandler::class.java)
        }
}