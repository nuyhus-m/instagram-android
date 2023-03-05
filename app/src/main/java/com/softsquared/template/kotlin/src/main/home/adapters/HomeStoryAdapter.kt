package com.softsquared.template.kotlin.src.main.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemHomeStoryBinding

class HomeStoryAdapter : RecyclerView.Adapter<HomeStoryAdapter.HomeStoryViewHolder>() {

    private val nicknameList = listOf("aaaa", "bbbb", "cccc", "dddd", "eeee", "ffff")

    inner class HomeStoryViewHolder(private val homeStoryItemBinding : ItemHomeStoryBinding)
        : RecyclerView.ViewHolder(homeStoryItemBinding.root) {
            fun bind(pos:Int) {
                if (pos == 0) {
                    homeStoryItemBinding.homeStoryRing.visibility = View.INVISIBLE
                    homeStoryItemBinding.homeStoryNickname.text = "내 스토리"
                }else {
                    homeStoryItemBinding.homeStoryNickname.text = nicknameList[pos-1]
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeStoryViewHolder {
        val binding = ItemHomeStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeStoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeStoryViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return nicknameList.size + 1
    }
}