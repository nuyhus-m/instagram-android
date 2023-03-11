package com.softsquared.template.kotlin.src.main.profile.adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.databinding.ItemProfilePostBinding

class ProfilePostAdapter(private val postList: List<String>) : RecyclerView.Adapter<ProfilePostAdapter.ProfilePostViewHolder>() {

    inner class ProfilePostViewHolder(private val profilePostItemBinding: ItemProfilePostBinding) :
        RecyclerView.ViewHolder(profilePostItemBinding.root) {
        fun bind(post: String) {
            Glide.with(itemView)
                .load(post)
                .into(profilePostItemBinding.profilePostPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostViewHolder {
        val binding = ItemProfilePostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfilePostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfilePostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}