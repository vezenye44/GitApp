package com.example.gitapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private lateinit var userList: List<UserDTO>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(parent)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(getItemByPosition(position))

    override fun getItemCount() = userList.size

    private fun getItemByPosition(position: Int) = userList[position]

    fun setData(newUserList: List<UserDTO>) {
        userList = newUserList
    }
}
