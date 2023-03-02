package com.example.gitapp

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private val data = mutableListOf<UserDTO>()
    init {
        setHasStableIds(true)
    }
    override fun getItemId(position: Int) = getItemByPosition(position).id
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(parent)
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(getItemByPosition(position))
    override fun getItemCount() = data.size
    private fun getItemByPosition(position: Int) = data[position]
    @SuppressLint("NotifyDataSetChanged")
    fun setData(userList: List<UserDTO>) {
        data.clear()
        data.addAll(userList)
        notifyDataSetChanged()
    }
}
