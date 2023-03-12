package com.softsquared.template.kotlin.src.main.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemFollowingBinding

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    inner class FollowingViewHolder(val followingItemBinding: ItemFollowingBinding) :
        RecyclerView.ViewHolder(followingItemBinding.root) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 10
    }
}