package com.example.gitapp.data.repo

import android.os.Handler
import android.os.Looper
import com.example.gitapp.domain.dto.UserDTO
import com.example.gitapp.domain.repo.UsersRepo

private const val DELAY_MILLIS = 5_000L

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
        Handler(Looper.getMainLooper()).postDelayed({
            //callbackError?.invoke(Throwable("ERROR"))
            callbackSuccess(users)
        }, DELAY_MILLIS)
    }
}