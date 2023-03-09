package com.example.gitapp.data.retrofit

import com.example.gitapp.domain.dto.UserDTO
import com.example.gitapp.domain.dto.UserProfileDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersRetrofitApi {
    @GET("users")
    fun getAllUsers(): Call<List<UserDTO>>

    @GET("users/{user}")
    fun getUserProfile(@Path("user") userLogin: String): Call<UserProfileDTO>
}