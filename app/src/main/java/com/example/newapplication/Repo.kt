package com.example.newapplication

import com.example.newapplication.Models.NewsModels
import com.example.newapplication.Networking.ApiProvider
import retrofit2.Response

class Repo {

    suspend fun newsProvider(
        country: String,
        category: String
    ): Response<NewsModels> {
       return ApiProvider.providerApi().apiCall(country=country, category = category)
    }
}