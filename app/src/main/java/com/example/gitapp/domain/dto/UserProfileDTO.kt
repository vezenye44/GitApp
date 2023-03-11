package com.example.gitapp.domain.dto

import com.google.gson.annotations.SerializedName

data class UserProfileDTO(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val name: String?,
    val blog: String?,
    val company: String?,
    val location: String?
)