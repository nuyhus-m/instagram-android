package com.softsquared.template.kotlin.src.main.home.adapters

import android.content.Intent
import android.util.Log
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
import com.softsquared.template.kotlin.src.main.home.LikeInterface
import com.softsquared.template.kotlin.src.main.home.LikeService
import com.softsquared.template.kotlin.src.main.home.models.LikeResponse
import com.softsquared.template.kotlin.src.main.home.models.ResultHomePost
import org.jetbrains.anko.image

class HomePostAdapter(private val postList: List<ResultHomePost>) : RecyclerView.Adapter<HomePostAdapter.HomePostViewHolder>(), LikeInterface {

    inner class HomePostViewHolder(private val homePostItemBinding: ItemHomePostBinding) :
        RecyclerView.ViewHolder(homePostItemBinding.root) {
        fun bind(post: ResultHomePost) {
            //프로필 사진
            Glide.with(itemView)
                .load(post.profilePicture)
                .into(homePostItemBinding.homePostProfile)
            //스토리
            if(post.userStoryOn == 0) {
                homePostItemBinding.homePostProfileRing.visibility = View.INVISIBLE
            }
            //닉네임
            homePostItemBinding.homePostNickname.text = post.profileName
            //좋아요
            if (post.likeOn.id != 0 && post.likeOn.on != 0) {
                Glide.with(itemView)
                    .load(R.drawable.ic_love_fill)
                    .into(homePostItemBinding.homePostHeart)
            }
            //스크랩
            if (post.scrapOn.id != 0 && post.scrapOn.on != 0) {
                Glide.with(itemView)
                    .load(R.drawable.ic_scrap_fill)
                    .into(homePostItemBinding.homePostScrap)
            }
            //좋아요 개수
            if(post.likeCount == 0) {
                homePostItemBinding.homePostHeartLayout.visibility = View.GONE
            } else {
                homePostItemBinding.homePostHeartText.text = "좋아요 ${post.likeCount}개"
            }
            //글 닉네임
            homePostItemBinding.homePostNickNameText.text = post.profileName
            //글 내용
            homePostItemBinding.homePostContent.text = post.content
            //댓글 모두 보기
            homePostItemBinding.homePostCommentText.text = "댓글 모두 보기"
            //작성일
            val month = post.createdAt.substring(5,7).toInt()
            val day = post.createdAt.substring(8,10).toInt()
            homePostItemBinding.homePostDate.text = "${month}월 ${day}일"
            //게시물 사진
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
            binding.homePostHeart.setOnClickListener {
                //좋아요
                if (postList[holder.adapterPosition].likeOn.id == 0 && postList[holder.adapterPosition].likeOn.on == 0) {
                    LikeService(this).tryPostLike(postList[holder.adapterPosition].postId)
                    Glide.with(parent)
                        .load(R.drawable.ic_love_fill)
                        .into(binding.homePostHeart)
                    binding.homePostHeartLayout.visibility = View.VISIBLE
                    binding.homePostHeartText.text = "좋아요 ${postList[holder.adapterPosition].likeCount + 1}개"
                }else {
                    if (postList[holder.adapterPosition].likeOn.id != 0 && postList[holder.adapterPosition].likeOn.on != 0) {
                        //좋아요가 눌러져있는 상태
                        LikeService(this).tryDeleteLike(postList[holder.adapterPosition].likeOn.id, false)
                        Glide.with(parent)
                            .load(R.drawable.ic_love)
                            .into(binding.homePostHeart)
                        if (postList[holder.adapterPosition].likeCount - 1 > 0) {
                            binding.homePostHeartText.text = "좋아요 ${postList[holder.adapterPosition].likeCount - 1}개"
                        } else {
                            binding.homePostHeartLayout.visibility = View.GONE
                        }
                    }else{
                        //좋아요가 눌리지않은 상태
                        LikeService(this).tryDeleteLike(postList[holder.adapterPosition].likeOn.id, true)
                        Glide.with(parent)
                            .load(R.drawable.ic_love_fill)
                            .into(binding.homePostHeart)
                        binding.homePostHeartLayout.visibility = View.VISIBLE
                        binding.homePostHeartText.text = "좋아요 ${postList[holder.adapterPosition].likeCount + 1}개"
                    }
                }
            }
            binding.homePostScrap.setOnClickListener {
                //스크랩
                if (postList[holder.adapterPosition].scrapOn.id == 0 && postList[holder.adapterPosition].scrapOn.on == 0) {
                    LikeService(this).tryPostScrap(postList[holder.adapterPosition].postId)
                    Glide.with(parent)
                        .load(R.drawable.ic_scrap_fill)
                        .into(binding.homePostScrap)
                }else {
                    if (postList[holder.adapterPosition].scrapOn.id != 0 && postList[holder.adapterPosition].scrapOn.on != 0) {
                        //스크랩되어있는 상태
                        LikeService(this).tryDeleteScrap(postList[holder.adapterPosition].scrapOn.id, false)
                        Glide.with(parent)
                            .load(R.drawable.ic_scrap)
                            .into(binding.homePostScrap)
                    } else {
                        //스크랩되어있지 않은 상태
                        LikeService(this).tryDeleteScrap(postList[holder.adapterPosition].scrapOn.id, true)
                        Glide.with(parent)
                            .load(R.drawable.ic_scrap_fill)
                            .into(binding.homePostScrap)
                    }
                }

            }
            //댓글 화면 전환
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

    override fun onPostLikeSuccess(response: LikeResponse) {
        Log.d("좋아요", response.message.toString())
    }

    override fun onPostLikeFailure(message: String) {
        Log.d("좋아요", message.toString())
    }
}