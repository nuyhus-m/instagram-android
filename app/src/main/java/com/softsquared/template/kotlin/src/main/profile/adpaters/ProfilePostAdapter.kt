package com.softsquared.template.kotlin.src.main.profile.adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemProfilePostBinding

class ProfilePostAdapter : RecyclerView.Adapter<ProfilePostAdapter.ProfilePostViewHolder>() {

    private val postList = listOf(
        com.softsquared.template.kotlin.R.drawable.ex_photo,
        com.softsquared.template.kotlin.R.drawable.ex_photo,
        com.softsquared.template.kotlin.R.drawable.ex_photo,
        com.softsquared.template.kotlin.R.drawable.ex_photo,
        com.softsquared.template.kotlin.R.drawable.ex_photo,
        com.softsquared.template.kotlin.R.drawable.ex_photo,
        com.softsquared.template.kotlin.R.drawable.ex_photo,
        com.softsquared.template.kotlin.R.drawable.ex_photo
    )

    inner class ProfilePostViewHolder(private val profilePostItemBinding: ItemProfilePostBinding) :
        RecyclerView.ViewHolder(profilePostItemBinding.root) {
        fun bind(post: Int) {
            profilePostItemBinding.profilePostPhoto.setImageResource(post)
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