package com.softsquared.template.kotlin.src.main.profile.adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.databinding.ItemProfilePostBinding
import com.softsquared.template.kotlin.src.main.MainActivity

class ProfilePostAdapter(private val postList: List<String>, val act: MainActivity) : RecyclerView.Adapter<ProfilePostAdapter.ProfilePostViewHolder>() {

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
        return ProfilePostViewHolder(binding).also {
            binding.profilePostPhoto.setOnClickListener {
                act.fragmentController("post", true, true)
            }
        }
    }

    override fun onBindViewHolder(holder: ProfilePostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}