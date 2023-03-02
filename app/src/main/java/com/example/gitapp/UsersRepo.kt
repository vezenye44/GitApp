package com.example.gitapp

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