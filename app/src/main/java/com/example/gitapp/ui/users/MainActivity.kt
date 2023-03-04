package com.example.gitapp.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitapp.domain.repo.UsersRepo
import com.example.gitapp.app
import com.example.gitapp.databinding.ActivityMainBinding
import com.example.gitapp.domain.dto.UserDTO


class MainActivity : AppCompatActivity(), UsersContract.View {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val presenter: UsersContract.Presenter by lazy { UsersPresenter(app().usersRepo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        presenter.attach(this)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun initView() {
        binding.activityMainRefreshFAB.setOnClickListener {
            presenter.onRefresh()
        }

        initRecyclerView()
    }



    override fun showData(users: List<UserDTO>) {
        adapter.setData(users)
    }

    override fun showError(it: Throwable) {
        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
    }

    override fun showLoadingProcess(inLoadingProcess: Boolean) {
        binding.activityMainProgressBar.isVisible = inLoadingProcess
        binding.activityMainUsersRecyclerView.isVisible = !inLoadingProcess

    }

    private fun initRecyclerView() {
        binding.activityMainUsersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.activityMainUsersRecyclerView.adapter = adapter
    }


}