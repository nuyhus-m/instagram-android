package com.softsquared.template.kotlin.src.main.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.databinding.ItemHomeStoryBinding
import com.softsquared.template.kotlin.src.main.home.models.ResultHomeStory

class HomeStoryAdapter(private val storyList: List<ResultHomeStory>) : RecyclerView.Adapter<HomeStoryAdapter.HomeStoryViewHolder>() {

    inner class HomeStoryViewHolder(private val homeStoryItemBinding: ItemHomeStoryBinding) :
        RecyclerView.ViewHolder(homeStoryItemBinding.root) {
        fun bind(story: ResultHomeStory, pos: Int) {
            if (pos == 0) {
                homeStoryItemBinding.homeStoryNickname.text = "내 스토리"
            } else {
                homeStoryItemBinding.homeStoryNickname.text = story.nickname
            }
            if (story.view_status == 1) {
                homeStoryItemBinding.homeStoryRing.visibility = View.INVISIBLE
            }
            Glide.with(itemView)
                .load(story.profile_image_url)
                .into(homeStoryItemBinding.homeStoryProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeStoryViewHolder {
        val binding =
            ItemHomeStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeStoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeStoryViewHolder, position: Int) {
        holder.bind(storyList[position], position)
    }

    override fun getItemCount(): Int {
        return storyList.size
    }
}