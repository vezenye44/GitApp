package com.example.gitapp.data.repo

import android.os.Handler
import android.os.Looper
import com.example.gitapp.domain.dto.UserProfileDTO
import com.example.gitapp.domain.repo.UserProfileRepo

private const val DELAY_MILLIS = 2_000L

private val users = listOf(
    UserProfileDTO(
        "mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4", "Patric",
        "www.blog.com",
        "www.geekbrains.ru",
        "Minsk"
    ),
    UserProfileDTO(
        "defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4", "Patric",
        "www.blog.com",
        "www.geekbrains.ru",
        "Minsk"
    ),
    UserProfileDTO(
        "pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4", "Patric",
        "www.blog.com",
        "www.geekbrains.ru",
        "Minsk"
    )
)

class FakeUserProfileRepoImpl(private val userLogin: String) : UserProfileRepo {
    override fun getUserProfile(
        callbackSuccess: (UserProfileDTO) -> Unit,
        callbackError: ((Throwable) -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            when(userLogin) {
                users[0].login -> callbackSuccess(users[0])
                users[1].login -> callbackSuccess(users[1])
                users[2].login -> callbackSuccess(users[2])
            }
        }, DELAY_MILLIS)
    }
}