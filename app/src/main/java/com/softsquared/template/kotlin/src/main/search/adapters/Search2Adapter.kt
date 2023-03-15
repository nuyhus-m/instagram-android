package com.softsquared.template.kotlin.src.main.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.databinding.ItemSearchBinding
import com.softsquared.template.kotlin.src.main.search.models.ResultSearch2

class Search2Adapter(val searchList: List<ResultSearch2>) : RecyclerView.Adapter<Search2Adapter.Search2ViewHolder>() {

    inner class Search2ViewHolder(private val searchItemBinding: ItemSearchBinding) :
        RecyclerView.ViewHolder(searchItemBinding.root) {
            fun bind(search:ResultSearch2) {
                searchItemBinding.searchNickName.text = search.nickname
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Search2ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Search2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Search2ViewHolder, position: Int) {
        holder.bind(searchList[position])
    }

    override fun getItemCount(): Int {
        return searchList.size
    }
}