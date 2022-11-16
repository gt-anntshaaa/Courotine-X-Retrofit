package com.example.courotinexretrofit.data.remote

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("movie/popular?api_key=6c7e1d637849839d12b5d92467f7c2d4&language=en-US")
    suspend fun getPopularMovie(@Query("page") page: String): Response<MovieResponse>
}