package com.example.gitapp.ui.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import coil.transform.CircleCropTransformation
import com.example.gitapp.R
import com.example.gitapp.data.repo.FakeUserProfileRepoImpl
import com.example.gitapp.databinding.ActivityUserProfileBinding
import com.example.gitapp.domain.dto.UserProfileDTO
import com.example.gitapp.domain.dto.UserProfilePresenter
import com.example.gitapp.ui.users.MainActivity

class UserProfileActivity : AppCompatActivity(), UserProfileContract.View {
    private lateinit var binding: ActivityUserProfileBinding
    private val presenter: UserProfileContract.Presenter by lazy {
        val userLogin = intent.getSerializableExtra(
            MainActivity.USER_PROFILE_EXTRA
        ).toString()
        UserProfilePresenter(
            FakeUserProfileRepoImpl(userLogin)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.attach(this)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun showLoadingProcess(inLoadingProcess: Boolean) {
        binding.activityUserProfileContainer.isVisible = !inLoadingProcess
        binding.activityUserProfileProgressBar.isVisible = inLoadingProcess
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun showData(data: UserProfileDTO) {
        with(binding) {
            activityUserProfileAvatarImageview.load(data.avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_image_placeholder)
                error(R.drawable.ic_image_error)
                transformations(CircleCropTransformation())
            }
            activityUserProfileLoginTextview.text = data.login
            activityUserProfileNameTextview.text = data.name
            activityUserProfileBlogTextview.apply {
                if (data.blog != null) {
                    visibility = View.VISIBLE
                    text = data.blog
                } else {
                    visibility = View.GONE
                }
            }
            activityUserProfileCompanyTextview.apply {
                if (data.company != null) {
                    visibility = View.VISIBLE
                    text = data.company
                } else {
                    this?.visibility = View.GONE
                }
            }
            activityUserProfileLocationTextview.apply {
                if (data.location != null) {
                    visibility = View.VISIBLE
                    text = data.location
                } else {
                    visibility = View.GONE
                }
            }
        }
    }
}