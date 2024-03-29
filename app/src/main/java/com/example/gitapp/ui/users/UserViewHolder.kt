package com.example.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gitapp.R
import com.example.gitapp.databinding.UsersRecyclerViewItemBinding
import com.example.gitapp.domain.dto.UserDTO

class UserViewHolder(parent: ViewGroup, private val clickListener: UserClickListener) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.users_recycler_view_item, parent, false)
    ) {

    private val binding = UsersRecyclerViewItemBinding.bind(itemView)

    fun bind(userDTO: UserDTO) {
        binding.usersRecyclerItemAvatarImageview.load(userDTO.avatarUrl) {
            crossfade(false)
            placeholder(R.drawable.ic_image_placeholder)
            error(R.drawable.ic_image_error)
        }
        binding.usersRecyclerItemLoginTextview.text = userDTO.login
        binding.usersRecyclerItemIdTextview.text = userDTO.id.toString()

        binding.usersRecyclerItemContainer.setOnClickListener {
            clickListener.onItemClick(layoutPosition)
        }
    }

}