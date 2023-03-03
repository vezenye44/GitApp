package com.example.gitapp.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersRetrofitClient {

    private val baseUrl = "https://api.github.com/"

    fun getRetrofitClient(): UsersRetrofitApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(UsersRetrofitApi::class.java)
    }
}