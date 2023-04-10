package com.example.gitapp.domain.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String
)