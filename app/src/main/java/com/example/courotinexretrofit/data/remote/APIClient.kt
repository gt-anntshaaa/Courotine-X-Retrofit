package com.example.courotinexretrofit.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: APIService = retrofit.create(APIService::class.java)
}