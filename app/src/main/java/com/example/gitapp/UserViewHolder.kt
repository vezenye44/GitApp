package com.example.gitapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.databinding.UsersRecyclerViewItemBinding

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.users_recycler_view_item, parent, false)
) {

    private val binding = UsersRecyclerViewItemBinding.bind(itemView)

    fun bind(user : UserDTO) {
        binding.usersRecyclerItemAvatarImageview
        binding.usersRecyclerItemLoginTextview.text = user.login
        binding.usersRecyclerItemIdTextview.text = user.id.toString()
    }

}