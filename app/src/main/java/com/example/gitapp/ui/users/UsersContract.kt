package com.example.gitapp.ui.users

import androidx.lifecycle.LiveData
import com.example.gitapp.domain.dto.UserDTO

interface UsersContract {

    interface ViewModel {
        val usersLiveData: LiveData<List<UserDTO>>
        val errorLiveData: LiveData<Throwable>
        val loadingLiveData: LiveData<Boolean>

        fun attach()
        fun onRefresh()
    }
}