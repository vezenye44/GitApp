package com.example.gitapp.ui.user

import com.example.gitapp.domain.dto.UserProfileDTO
import com.example.gitapp.domain.repo.UserProfileRepo
import com.example.gitapp.ui.user.UserProfileContract.View

class UserProfilePresenter(
    private val userProfileRepo: UserProfileRepo
) : UserProfileContract.Presenter {
    private var view: View? = null
    private var user: UserProfileDTO? = null
    private var isLoadingProcess = false
    private var throwable: Throwable? = null

    override fun attach(view: View) {
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
            ::onSuccessData,
            ::onErrorData
        )
    }

    private fun onSuccessData(profileDTO: UserProfileDTO) {
        view?.showLoadingProcess(false)
        view?.showData(profileDTO)
        saveViewState(false, user = profileDTO)
    }

    private fun onErrorData(throwable: Throwable) {
        view?.showLoadingProcess(false)
        view?.showError(throwable)
        saveViewState(false, throwable = throwable)
    }

    override fun detach() {
        this.view = null
    }


}