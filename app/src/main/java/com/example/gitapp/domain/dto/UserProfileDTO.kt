package com.example.gitapp.domain.dto

import com.google.gson.annotations.SerializedName

data class UserProfileDTO(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val name: String? = null,
    val blog: String? = null,
    val company: String? = null,
    val location: String? = null
)