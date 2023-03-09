package com.example.gitapp.ui.users

import com.example.gitapp.domain.dto.UserDTO

interface UsersContract {

    interface View {
        fun showData(users: List<UserDTO>)
        fun showError(throwable: Throwable)
        fun showLoadingProcess(inLoadingProcess: Boolean)
        fun showToast(message: String)
        fun openUserProfile(userProfileUrl: String)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefresh()
        fun onItemClick(itemPosition: Int)
    }
}