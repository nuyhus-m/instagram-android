package com.softsquared.template.kotlin.src.main.home.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.softsquared.template.kotlin.databinding.ItemHomePostBinding
import com.softsquared.template.kotlin.src.comment.CommentActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment

class HomePostAdapter : RecyclerView.Adapter<HomePostAdapter.HomePostViewHolder>() {

    private val nicknameList = listOf<String>("aaaa", "bbbb", "cccc", "dddd", "eeee", "ffff")

    inner class HomePostViewHolder(private val homePostItemBinding: ItemHomePostBinding) :
        RecyclerView.ViewHolder(homePostItemBinding.root) {
        fun bind(nickname: String) {
            homePostItemBinding.homePostNickname.text = nickname

            homePostItemBinding.homePostPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            homePostItemBinding.homePostPager.adapter = HomePostPagerAdapter()

            homePostItemBinding.indicator.setViewPager(homePostItemBinding.homePostPager)
            homePostItemBinding.indicator.createIndicators(3, 0)
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
        holder.bind(nicknameList[position])
    }

    override fun getItemCount(): Int {
        return nicknameList.size
    }
}