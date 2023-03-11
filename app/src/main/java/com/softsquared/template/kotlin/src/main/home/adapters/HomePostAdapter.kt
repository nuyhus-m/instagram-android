package com.softsquared.template.kotlin.src.main.home.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
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

            homePostItemBinding.homePostPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            homePostItemBinding.homePostPager.adapter = HomePostPagerAdapter(post.photos)

            homePostItemBinding.indicator.setViewPager(homePostItemBinding.homePostPager)
            homePostItemBinding.indicator.createIndicators(post.photos.size, 0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePostViewHolder {
        val binding =
            ItemHomePostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomePostViewHolder(binding).also {
            binding.homePostComment.setOnClickListener {
                startActivity(it.context, Intent(it.context, CommentActivity::class.java), null)
            }
            binding.homePostText.setOnClickListener {
                startActivity(it.context, Intent(it.context, CommentActivity::class.java), null)
            }
            binding.homePostCommentText.setOnClickListener {
                startActivity(it.context, Intent(it.context, CommentActivity::class.java), null)
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