package com.example.gitapp.data.retrofit

import com.example.gitapp.domain.dto.UserDTO
import retrofit2.Call
import retrofit2.http.GET

interface UsersRetrofitApi {
    @GET("users")
    fun getAllUsers(): Call<List<UserDTO>>
}