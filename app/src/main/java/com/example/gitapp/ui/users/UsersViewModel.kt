package com.example.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitapp.domain.dto.UserDTO
import com.example.gitapp.domain.repo.UsersRepo
import com.example.gitapp.ui.custom.livedata.SingleEventLiveData

class UsersViewModel(
    private val usersRepo: UsersRepo
) : UsersContract.ViewModel {

    override val usersLiveData: LiveData<List<UserDTO>> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = SingleEventLiveData()
    override val loadingLiveData: LiveData<Boolean> = MutableLiveData()

    private var isFirstAttach: Boolean = true
    override fun attach() {
        if (isFirstAttach) {
            isFirstAttach = false
            loadData()
        }
    }

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        loadingLiveData.mutable().postValue(true)
        usersRepo.getUsers(
            callbackSuccess = {
                loadingLiveData.mutable().postValue(false)
                usersLiveData.mutable().postValue(it)
            },
            callbackError = {
                loadingLiveData.mutable().postValue(false)
                errorLiveData.mutable().postValue(it)
            }
        )
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw java.lang.IllegalStateException("This isn't MutableLiveData")
    }
}