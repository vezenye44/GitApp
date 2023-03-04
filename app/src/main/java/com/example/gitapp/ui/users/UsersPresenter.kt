package com.example.gitapp.ui.users

import com.example.gitapp.domain.repo.UsersRepo

class UsersPresenter(
    private val usersRepo: UsersRepo
): UsersContract.Presenter {
    private var view: UsersContract.View? = null

    override fun attach(view: UsersContract.View) {
        this.view = view
        loadData()
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        view?.showLoadingProcess(true)
        usersRepo.getUsers(
            callbackSuccess = {
                view?.showLoadingProcess(false)
                view?.showData(it)
            },
            callbackError = {
                view?.showLoadingProcess(false)
                view?.showError(it)
            }
        )
    }
}