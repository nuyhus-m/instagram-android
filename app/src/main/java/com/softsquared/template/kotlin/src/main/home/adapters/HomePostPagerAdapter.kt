package com.softsquared.template.kotlin.src.main.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.databinding.ItemHomePostPagerBinding

class HomePostPagerAdapter : RecyclerView.Adapter<HomePostPagerAdapter.HomePostPagerViewHolder>() {

    private val photoList = listOf(R.drawable.ex_photo, R.drawable.ex_photo, R.drawable.ex_photo)

    inner class HomePostPagerViewHolder(private val homePostPagerItemBinding: ItemHomePostPagerBinding) :
        RecyclerView.ViewHolder(homePostPagerItemBinding.root) {
        fun bind(photo: Int) {
            homePostPagerItemBinding.homePostPagerImg.setImageResource(photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePostPagerViewHolder {
        val binding =
            ItemHomePostPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomePostPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomePostPagerViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}