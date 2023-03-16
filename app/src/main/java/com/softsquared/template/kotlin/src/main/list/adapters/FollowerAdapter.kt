package com.softsquared.template.kotlin.src.main.list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.databinding.ItemFollowerBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.list.models.Follower

class FollowerAdapter(private val followerList: List<Follower>, private val act: MainActivity) : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {

    inner class FollowerViewHolder(private val followerItemBinding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(followerItemBinding.root) {
        fun bind(follower: Follower) {
            if(follower.story_status == 0){
                followerItemBinding.followerPhotoRing.visibility = View.INVISIBLE
            }
            Glide.with(itemView)
                .load(follower.profile_image_url)
                .into(followerItemBinding.followerPhoto)
            followerItemBinding.followerNickName.text = follower.nickname
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FollowerViewHolder(binding).also { holder ->
            binding.followerLayout.setOnClickListener {
                val editor = ApplicationClass.sSharedPreferences.edit()
                editor.putInt("aUserId", followerList[holder.adapterPosition].user_id)
                editor.putInt("tabItem", 0)
                editor.apply()
                act.fragmentController("user", false, true)
            }
        }
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(followerList[position])
    }

    override fun getItemCount(): Int {
        return followerList.size
    }
}