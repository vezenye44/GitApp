package com.example.gitapp.ui.user

import com.example.gitapp.domain.dto.UserProfileDTO
import com.example.gitapp.domain.repo.UserProfileRepo

class UserProfilePresenter(
    private val userProfileRepo: UserProfileRepo
) : UserProfileContract.Presenter {
    private var view: UserProfileContract.View? = null
    private var user: UserProfileDTO? = null
    private var isLoadingProcess = false
    private var throwable: Throwable? = null

    override fun attach(view: UserProfileContract.View) {
        this.view = view

        view.showLoadingProcess(isLoadingProcess)
        if (user != null) {
            view.showData(user!!)
        } else if (throwable == null) {
            loadData()
        }
    }

    private fun saveViewState(
        isLoadingProcess: Boolean,
        user: UserProfileDTO? = null,
        throwable: Throwable? = null
    ) {
        this.isLoadingProcess = isLoadingProcess
        this.user = user
        this.throwable = throwable
    }

    private fun loadData() {
        view?.showLoadingProcess(true)
        saveViewState(true)
        userProfileRepo.getUserProfile(
            callbackSuccess = {
                view?.showLoadingProcess(false)
                view?.showData(it)
                saveViewState(false, user=it)
            },
            callbackError = {
                view?.showLoadingProcess(false)
                view?.showError(it)
                saveViewState(false, throwable=it)
            }
        )
    }

    override fun detach() {
        this.view = null
    }


}