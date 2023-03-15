package com.softsquared.template.kotlin.src.main.search.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.databinding.ItemSearchBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.search.models.ResultSearch2

class Search2Adapter(private val searchList: List<ResultSearch2>, private val act: MainActivity) : RecyclerView.Adapter<Search2Adapter.Search2ViewHolder>() {

    inner class Search2ViewHolder(private val searchItemBinding: ItemSearchBinding) :
        RecyclerView.ViewHolder(searchItemBinding.root) {
            fun bind(search:ResultSearch2) {
                Glide.with(itemView)
                    .load(search.profile_image_url)
                    .into(searchItemBinding.searchPhoto)
                searchItemBinding.searchNickName.text = search.nickname
                if(search.name != null) {
                    searchItemBinding.searchName.text = search.name.toString()
                } else {
                    searchItemBinding.searchName.visibility = View.GONE
                }
                when(search.connected_count) {
                    0 -> searchItemBinding.searchConnected.visibility = View.GONE
                    1 -> searchItemBinding.searchConnected.text = "${search.connected_friend_nickname}님이 팔로우합니다."
                    else -> searchItemBinding.searchConnected.text = "${search.connected_friend_nickname}님 외 ${search.connected_count}명이 팔로우합니다."
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Search2ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Search2ViewHolder(binding).also { holder ->
            binding.searchLayout.setOnClickListener {
                val editor = ApplicationClass.sSharedPreferences.edit()
                editor.putInt("aUserId", searchList[holder.adapterPosition].user_id)
                editor.putInt("tabItem", 2)
                editor.apply()
                act.fragmentController("user", true, true)
            }
        }
    }

    override fun onBindViewHolder(holder: Search2ViewHolder, position: Int) {
        holder.bind(searchList[position])
    }

    override fun getItemCount(): Int {
        return searchList.size
    }
}