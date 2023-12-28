package dev.ozkan.ratingapplication.core.retrofit

import dev.ozkan.ratingapplication.app.home.HomeApiHandler
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainRetrofitInstance {


    val api : HomeApiHandler by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.187:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HomeApiHandler::class.java)
    }
}