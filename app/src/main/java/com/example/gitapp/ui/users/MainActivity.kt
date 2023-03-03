package com.example.gitapp.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitapp.domain.UsersRepo
import com.example.gitapp.app
import com.example.gitapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val usersRepo: UsersRepo by lazy { app().usersRepo }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.activityMainRefreshFAB.setOnClickListener {
            showLoadingProcess(true)
            loadData()
        }

        initRecyclerView()
    }

    private fun loadData() {
        usersRepo.getUsers(
            callbackSuccess = {
                showLoadingProcess(false)
                adapter.setData(it)
            },
            callbackError = {
                showLoadingProcess(false)
                showError(it)
            }
        )
    }

    private fun showError(it: Throwable) {
        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
    }

    private fun showLoadingProcess(inLoadingProcess: Boolean) {
        binding.activityMainProgressBar.isVisible = inLoadingProcess
        binding.activityMainUsersRecyclerView.isVisible = !inLoadingProcess

    }

    private fun initRecyclerView() {
        binding.activityMainUsersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.activityMainUsersRecyclerView.adapter = adapter
        showLoadingProcess(true)
        loadData()
    }


}