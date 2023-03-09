package com.example.gitapp.domain.dto

import com.example.gitapp.domain.repo.UserProfileRepo
import com.example.gitapp.ui.user.UserProfileContract

class UserProfilePresenter(
    private val userProfileRepo: UserProfileRepo
) : UserProfileContract.Presenter {
    private var view: UserProfileContract.View? = null

    override fun attach(view: UserProfileContract.View) {
        this.view = view

        loadData(view)
    }

    private fun loadData(view: UserProfileContract.View) {
        view?.showLoadingProcess(true)
        userProfileRepo.getUserProfile(
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

    override fun detach() {
        this.view = null
    }


}