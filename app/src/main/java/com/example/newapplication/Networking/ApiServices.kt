package com.example.newapplication.Networking

import com.example.newapplication.Models.NewsModels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

//    https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=7a737b14073b4ef69b6af84f02f8a116

    @GET("top-headlines")
    suspend fun apiCall(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = "7a737b14073b4ef69b6af84f02f8a116"
    ): Response<NewsModels>
}