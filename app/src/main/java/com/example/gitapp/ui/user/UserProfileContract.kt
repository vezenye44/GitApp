package com.example.gitapp.ui.user

import com.example.gitapp.domain.dto.UserProfileDTO

interface UserProfileContract {
    interface View {
        fun showData(data: UserProfileDTO)
        fun showError(throwable: Throwable)
        fun showLoadingProcess(inLoadingProcess: Boolean)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
    }
}