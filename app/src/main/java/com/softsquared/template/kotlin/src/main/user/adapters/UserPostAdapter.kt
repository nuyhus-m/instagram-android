package com.softsquared.template.kotlin.src.main.user.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemProfilePostBinding

class UserPostAdapter : RecyclerView.Adapter<UserPostAdapter.UserPostViewHolder>() {

    inner class UserPostViewHolder(private val profilePostItemBinding: ItemProfilePostBinding) :
        RecyclerView.ViewHolder(profilePostItemBinding.root) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostViewHolder {
        val binding = ItemProfilePostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserPostViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 10
    }

}