package com.example.gitapp.domain.repo

import com.example.gitapp.domain.dto.UserDTO

interface UsersRepo {
    //Create
    //Reade
    //Update
    //Delete

    fun getUsers(
        callbackSuccess : (List<UserDTO>) -> Unit,
        callbackError : ((Throwable) -> Unit)? = null
    )
}