package dev.ozkan.ratingapplication.core.retrofit

import dev.ozkan.ratingapplication.API
import dev.ozkan.ratingapplication.core.home.HomeApiHandler
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainRetrofitInstance {


    val api : HomeApiHandler by lazy {
        Retrofit.Builder()
            .baseUrl(API.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HomeApiHandler::class.java)
    }
}