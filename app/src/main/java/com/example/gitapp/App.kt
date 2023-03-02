package com.example.gitapp

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment

class App : Application() {
    val usersRepo: UsersRepo by lazy { FakeUsersRepoImpl()}
}
fun Activity.app() = (applicationContext as App)
fun Fragment.app() = (requireContext().applicationContext as App)