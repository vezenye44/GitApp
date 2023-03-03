package com.example.gitapp.data.repo

import com.example.gitapp.data.retrofit.UsersRetrofitClient
import com.example.gitapp.domain.dto.UserDTO
import com.example.gitapp.domain.repo.UsersRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitUsersRepoImpl : UsersRepo {

    private val retrofitClient = UsersRetrofitClient()

    override fun getUsers(
        callbackSuccess: (List<UserDTO>) -> Unit,
        callbackError: ((Throwable) -> Unit)?
    ) {
        retrofitClient.getRetrofitClient()
            .getAllUsers()
            .enqueue(object : Callback<List<UserDTO>> {
                override fun onResponse(
                    call: Call<List<UserDTO>>,
                    response: Response<List<UserDTO>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let(callbackSuccess)
                    } else {
                        val message = response.message()
                        if (callbackError != null) {
                            callbackError(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<List<UserDTO>>, t: Throwable) {
                    if (callbackError != null) {
                        callbackError(t)
                    }
                }
            })

    }
}