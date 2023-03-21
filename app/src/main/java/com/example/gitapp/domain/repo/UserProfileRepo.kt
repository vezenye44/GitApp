package com.example.gitapp.domain.repo

import com.example.gitapp.domain.dto.UserProfileDTO

interface UserProfileRepo {
    fun getUserProfile(
        callbackSuccess: (UserProfileDTO) -> Unit,
        callbackError: ((Throwable) -> Unit)? = null
    )
}