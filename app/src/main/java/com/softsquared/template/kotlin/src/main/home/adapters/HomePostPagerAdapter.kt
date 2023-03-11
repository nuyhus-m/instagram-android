package com.softsquared.template.kotlin.src.main.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.databinding.ItemHomePostPagerBinding
import com.softsquared.template.kotlin.src.main.home.models.Photo

class HomePostPagerAdapter(val photos: List<Photo>) : RecyclerView.Adapter<HomePostPagerAdapter.HomePostPagerViewHolder>() {

    inner class HomePostPagerViewHolder(private val homePostPagerItemBinding: ItemHomePostPagerBinding) :
        RecyclerView.ViewHolder(homePostPagerItemBinding.root) {
        fun bind(photo: Photo) {
            Glide.with(itemView)
                .load(photo.photoUrl)
                .into(homePostPagerItemBinding.homePostPagerImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePostPagerViewHolder {
        val binding =
            ItemHomePostPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomePostPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomePostPagerViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }
}