package com.example.gitapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityMainUsersRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = UsersAdapter().apply {
                setData(
                    listOf<UserDTO>(
                        UserDTO("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
                        UserDTO("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
                        UserDTO("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4")
                    )
                )
            }
        }

    }
}