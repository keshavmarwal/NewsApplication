package com.example.newapplication.Networking

import com.example.newapplication.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    val okHttpClient= okhttp3.OkHttpClient.Builder().build()

    fun providerApi() = Retrofit.Builder().baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build().create(ApiServices::class.java)            //Instance of retrofit is created...
}