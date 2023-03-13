package com.softsquared.template.kotlin.src.main.list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.databinding.ItemFollowingBinding
import com.softsquared.template.kotlin.src.main.list.models.Following

class FollowingAdapter(private val followingList: List<Following>) : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    inner class FollowingViewHolder(private val followingItemBinding: ItemFollowingBinding) :
        RecyclerView.ViewHolder(followingItemBinding.root) {
        fun bind(following: Following) {
            if(following.story_status == 0){
                followingItemBinding.followingPhotoRing.visibility = View.INVISIBLE
            }
            Glide.with(itemView)
                .load(following.profile_image_url)
                .into(followingItemBinding.followingPhoto)
            followingItemBinding.followingNickName.text = following.nickname
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(followingList[position])
    }

    override fun getItemCount(): Int {
        return followingList.size
    }
}