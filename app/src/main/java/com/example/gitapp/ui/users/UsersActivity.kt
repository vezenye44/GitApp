package com.example.gitapp.ui.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitapp.app
import com.example.gitapp.databinding.ActivityUsersBinding
import com.example.gitapp.domain.dto.UserDTO
import com.example.gitapp.ui.user.UserProfileActivity


class UsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersBinding
    private val adapter: UsersAdapter by lazy {
        UsersAdapter(UserClickListener() {
            openUserProfile(it)
        })
    }
    private lateinit var viewModel: UsersContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = extractPresenter()
        with(viewModel) {
            usersLiveData.observe(this@UsersActivity) {
                showData(it)
            }
            errorLiveData.observe(this@UsersActivity) {
                showError(it)
            }
            loadingLiveData.observe(this@UsersActivity) {
                showLoadingProcess(it)
            }
        }

        viewModel.attach()


    }

    private fun extractPresenter(): UsersContract.ViewModel {
        return lastCustomNonConfigurationInstance as? UsersContract.ViewModel
            ?: UsersViewModel(app().usersRepo)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initView() {
        binding.activityMainRefreshFAB.setOnClickListener {
            viewModel.onRefresh()
        }

        initRecyclerView()
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersContract.ViewModel {
        return viewModel
    }

    private fun showData(users: List<UserDTO>) {
        adapter.setData(users)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
    }

    private fun showLoadingProcess(inLoadingProcess: Boolean) {
        binding.activityMainProgressBar.isVisible = inLoadingProcess
        binding.activityMainUsersRecyclerView.isVisible = !inLoadingProcess

    }

    private fun openUserProfile(userPosition: Int) {
        val login = viewModel.usersLiveData.value?.get(userPosition)?.login
        val intent = Intent(this, UserProfileActivity::class.java)
        intent.putExtra(USER_PROFILE_EXTRA, login)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        binding.activityMainUsersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.activityMainUsersRecyclerView.adapter = adapter
    }

    companion object {
        const val USER_PROFILE_EXTRA = "user_profile_extra"
    }
}

fun interface UserClickListener {
    fun onItemClick(position: Int)
}