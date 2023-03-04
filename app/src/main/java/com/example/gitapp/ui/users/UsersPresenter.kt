package com.example.gitapp.ui.users

import com.example.gitapp.domain.dto.UserDTO
import com.example.gitapp.domain.repo.UsersRepo

class UsersPresenter(
    private val usersRepo: UsersRepo
) : UsersContract.Presenter {
    private var view: UsersContract.View? = null
    private var users: List<UserDTO>? = null
    private var isLoadingProcess: Boolean = false
    private var throwable: Throwable? = null

    override fun attach(view: UsersContract.View) {
        this.view = view
        view.showLoadingProcess(isLoadingProcess)
        if (users != null) {
            view.showData(users!!)
        } else if (throwable == null){
            loadData()
        }
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        loadData()
    }

    private fun saveViewState(
        isLoadingProcess: Boolean,
        users: List<UserDTO>? = null,
        throwable: Throwable? = null
    ) {
        this.isLoadingProcess = isLoadingProcess
        this.users = users
        this.throwable = throwable
    }

    private fun loadData() {
        view?.showLoadingProcess(true)
        saveViewState(true)
        usersRepo.getUsers(
            callbackSuccess = {
                view?.showLoadingProcess(false)
                saveViewState(false, users=it)
                view?.showData(it)
            },
            callbackError = {
                view?.showLoadingProcess(false)
                saveViewState(false, throwable=it)
                view?.showError(it)
            }
        )
    }
}