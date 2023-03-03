package com.example.gitapp

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.gitapp.data.repo.RetrofitUsersRepoImpl
import com.example.gitapp.domain.repo.UsersRepo

class App : Application() {
    val usersRepo: UsersRepo by lazy { RetrofitUsersRepoImpl() }
}
fun Activity.app() = (applicationContext as App)
fun Fragment.app() = (requireContext().applicationContext as App)