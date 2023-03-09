package com.example.gitapp.data.repo

import com.example.gitapp.data.retrofit.UsersRetrofitClient
import com.example.gitapp.domain.dto.UserProfileDTO
import com.example.gitapp.domain.repo.UserProfileRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitUserProfileRepoImpl(private val userLogin: String) : UserProfileRepo {

    private val retrofitClient = UsersRetrofitClient()

    override fun getUserProfile(
        callbackSuccess: (UserProfileDTO) -> Unit,
        callbackError: ((Throwable) -> Unit)?
    ) {
        retrofitClient.getRetrofitClient()
            .getUserProfile(userLogin)
            .enqueue(object : Callback<UserProfileDTO> {
                override fun onResponse(
                    call: Call<UserProfileDTO>,
                    response: Response<UserProfileDTO>
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

                override fun onFailure(call: Call<UserProfileDTO>, t: Throwable) {
                    if (callbackError != null) {
                        callbackError(t)
                    }
                }
            })
    }
}