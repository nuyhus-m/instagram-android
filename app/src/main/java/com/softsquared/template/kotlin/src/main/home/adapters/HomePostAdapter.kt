package com.softsquared.template.kotlin.src.main.home.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.databinding.ItemHomePostBinding
import com.softsquared.template.kotlin.src.comment.CommentActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.home.models.ResultHomePost

class HomePostAdapter(val postList: List<ResultHomePost>) : RecyclerView.Adapter<HomePostAdapter.HomePostViewHolder>() {

    inner class HomePostViewHolder(private val homePostItemBinding: ItemHomePostBinding) :
        RecyclerView.ViewHolder(homePostItemBinding.root) {
        fun bind(post: ResultHomePost) {
            Glide.with(itemView)
                .load(post.profilePicture)
                .into(homePostItemBinding.homePostProfile)

            homePostItemBinding.homePostNickname.text = post.profileName
            if(post.likeCount == 0) {
                homePostItemBinding.homePostHeartLayout.visibility = View.GONE
            } else {
                homePostItemBinding.homePostHeartText.text = "좋아요 ${post.likeCount}개"
            }
            homePostItemBinding.homePostNickNameText.text = post.profileName
            homePostItemBinding.homePostContent.text = post.content
            homePostItemBinding.homePostCommentText.text = "댓글 모두 보기"
            val month = post.createdAt.substring(5,7).toInt()
            val day = post.createdAt.substring(8,10).toInt()
            homePostItemBinding.homePostDate.text = "${month}월 ${day}일"

            homePostItemBinding.homePostPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            homePostItemBinding.homePostPager.adapter = HomePostPagerAdapter(post.photos)

            homePostItemBinding.indicator.setViewPager(homePostItemBinding.homePostPager)
            homePostItemBinding.indicator.createIndicators(post.photos.size, 0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePostViewHolder {
        val binding =
            ItemHomePostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomePostViewHolder(binding).also { holder ->
            binding.homePostComment.setOnClickListener {
                val intent = Intent(it.context, CommentActivity::class.java)
                intent.putExtra("photo", postList[holder.adapterPosition].profilePicture)
                intent.putExtra("nickName", postList[holder.adapterPosition].profileName)
                intent.putExtra("content", postList[holder.adapterPosition].content)
                intent.putExtra("postId", postList[holder.adapterPosition].postId)
                startActivity(it.context, intent, null)
            }
            binding.homePostContent.setOnClickListener {
                val intent = Intent(it.context, CommentActivity::class.java)
                intent.putExtra("photo", postList[holder.adapterPosition].profilePicture)
                intent.putExtra("nickName", postList[holder.adapterPosition].profileName)
                intent.putExtra("content", postList[holder.adapterPosition].content)
                intent.putExtra("postId", postList[holder.adapterPosition].postId)
                startActivity(it.context, intent, null)
            }
            binding.homePostCommentText.setOnClickListener {
                val intent = Intent(it.context, CommentActivity::class.java)
                intent.putExtra("photo", postList[holder.adapterPosition].profilePicture)
                intent.putExtra("nickName", postList[holder.adapterPosition].profileName)
                intent.putExtra("content", postList[holder.adapterPosition].content)
                intent.putExtra("postId", postList[holder.adapterPosition].postId)
                startActivity(it.context, intent, null)
            }
        }
    }

    override fun onBindViewHolder(holder: HomePostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}