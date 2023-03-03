package com.example.gitapp

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.gitapp.data.usersrepo.fake.FakeUsersRepoImpl
import com.example.gitapp.domain.UsersRepo

class App : Application() {
    val usersRepo: UsersRepo by lazy { FakeUsersRepoImpl() }
}
fun Activity.app() = (applicationContext as App)
fun Fragment.app() = (requireContext().applicationContext as App)