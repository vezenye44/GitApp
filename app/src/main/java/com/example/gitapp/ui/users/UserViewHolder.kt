package com.example.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gitapp.R
import com.example.gitapp.domain.UserDTO
import com.example.gitapp.databinding.UsersRecyclerViewItemBinding

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.users_recycler_view_item, parent, false)
) {

    private val binding = UsersRecyclerViewItemBinding.bind(itemView)

    fun bind(userDTO: UserDTO) {
        binding.usersRecyclerItemAvatarImageview.load(userDTO.avatar_url){
            crossfade(true)
            placeholder(R.drawable.ic_image_placeholder)
            error(R.drawable.ic_image_error)
        }
        binding.usersRecyclerItemLoginTextview.text = userDTO.login
        binding.usersRecyclerItemIdTextview.text = userDTO.id.toString()
    }

}