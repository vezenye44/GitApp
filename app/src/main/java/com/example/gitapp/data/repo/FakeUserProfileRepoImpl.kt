package com.example.gitapp.data.repo

import android.os.Handler
import android.os.Looper
import com.example.gitapp.domain.dto.UserProfileDTO
import com.example.gitapp.domain.repo.UserProfileRepo

private const val DELAY_MILLIS = 2_000L

private val user =
    UserProfileDTO(
        "mojombo",
        1,
        "https://avatars.githubusercontent.com/u/1?v=4",
        "Patric",
        "www.blog.com",
        "www.geekbrains.ru",
        "Minsk"
    )

class FakeUserProfileRepoImpl(private val userLogin: String) : UserProfileRepo {
    override fun getUserProfile(
        callbackSuccess: (UserProfileDTO) -> Unit,
        callbackError: ((Throwable) -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            callbackSuccess(user)
        }, DELAY_MILLIS)
    }
}