package com.softsquared.template.kotlin.src.main.profile.adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemProfileStoryBinding

class ProfileStoryAdapter : RecyclerView.Adapter<ProfileStoryAdapter.ProfileStoryViewHolder>() {

    inner class ProfileStoryViewHolder(private val profileStoryItemBinding: ItemProfileStoryBinding) :
        RecyclerView.ViewHolder(profileStoryItemBinding.root) {
        fun bind() {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileStoryViewHolder {
        val binding = ItemProfileStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileStoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileStoryViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 4
    }
}