package com.example.gitapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.gitapp.databinding.UsersRecyclerViewItemBinding

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.users_recycler_view_item, parent, false)
) {

    private val binding = UsersRecyclerViewItemBinding.bind(itemView)

    fun bind(user: UserDTO) {
        binding.usersRecyclerItemAvatarImageview.load(user.avatar_url){
            crossfade(true)
            placeholder(R.drawable.ic_image_placeholder)
            error(R.drawable.ic_image_error)
        }
        binding.usersRecyclerItemLoginTextview.text = user.login
        binding.usersRecyclerItemIdTextview.text = user.id.toString()
    }

}