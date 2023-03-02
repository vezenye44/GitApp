package com.example.gitapp

import android.os.Handler
import android.os.Looper

class FakeUsersRepoImpl : UsersRepo {

    private val users = listOf(
        UserDTO("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        UserDTO("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        UserDTO("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4")
    )

    override fun getUsers(
        callbackSuccess: (List<UserDTO>) -> Unit,
        callbackError: ((Throwable) -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).postDelayed({callbackSuccess(users)}, DELAY_MILLIS)
    }

    companion object {
        private const val DELAY_MILLIS = 2_000L
    }
}