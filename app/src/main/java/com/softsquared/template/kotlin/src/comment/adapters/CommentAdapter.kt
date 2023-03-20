package com.softsquared.template.kotlin.src.comment.adapters

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.databinding.ItemCommentBinding
import com.softsquared.template.kotlin.src.comment.CommentActivity
import com.softsquared.template.kotlin.src.comment.CommentFragmentInterface
import com.softsquared.template.kotlin.src.comment.CommentService
import com.softsquared.template.kotlin.src.comment.models.AddCommentRequest
import com.softsquared.template.kotlin.src.comment.models.CommentResponse
import com.softsquared.template.kotlin.src.comment.models.ResultComment
import com.softsquared.template.kotlin.src.main.home.models.LikeResponse

class CommentAdapter(private var commentList: List<ResultComment>, private val postCommentsInterface: CommentActivity) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(), CommentFragmentInterface {

    var commentChildList: List<ResultComment> = listOf()

    inner class CommentViewHolder(private val commentItemBinding: ItemCommentBinding)
        :RecyclerView.ViewHolder(commentItemBinding.root){
            fun bind(comment: ResultComment) {
                // 스토리 테두리
                commentItemBinding.commentRing.visibility = View.INVISIBLE
                // 프로필 사진
                Glide.with(itemView)
                    .load(comment.profilePicture)
                    .into(commentItemBinding.commentPhoto)
                // 프로필 이름
                commentItemBinding.commentProfileName.text = comment.profileName
                // 댓글
                commentItemBinding.commentContent.text = comment.comment
                // 좋아요
                if (comment.likeOn.id != 0 && comment.likeOn.on != 0) {
                    Glide.with(itemView)
                        .load(R.drawable.ic_love_fill)
                        .into(commentItemBinding.commentHeartIc)
                }
                if (comment.likeCount != 0) {
                    commentItemBinding.commentHeartNum.text = comment.likeCount.toString()
                }
                //답글
                if(comment.bigCommentCount == 0) {
                    commentItemBinding.commentChildLayout.visibility = View.GONE
                }else {
                    commentItemBinding.commentChildNum.text = "답글 ${comment.bigCommentCount}개 보기"
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding).also { holder ->
            binding.commentChildNum.setOnClickListener {
                CommentService(this).tryGetChildComments(commentList[holder.adapterPosition].commentId)
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.commentChildNum.visibility = View.GONE
                    binding.commentClildLine.visibility = View.GONE
                    binding.commentChildRv.layoutManager = LinearLayoutManager(parent.context)
                    binding.commentChildRv.adapter = CommentAdapter(commentChildList, postCommentsInterface)
                }, 500)
            }
            binding.commentHeartIc.setOnClickListener {
                if (commentList[holder.adapterPosition].likeOn.id == 0 && commentList[holder.adapterPosition].likeOn.on == 0) {
                    CommentService(this).tryPostCommentLike(commentList[holder.adapterPosition].commentId)
                    Glide.with(parent)
                        .load(R.drawable.ic_love_fill)
                        .into(binding.commentHeartIc)
                    val newNum = commentList[holder.adapterPosition].likeCount + 1
                    binding.commentHeartNum.text = "$newNum"
                } else {
                    if (commentList[holder.adapterPosition].likeOn.id != 0 && commentList[holder.adapterPosition].likeOn.on != 0) {
                        //좋아요 눌러져 있는 상태
                        CommentService(this).tryPatchCommentLike(commentList[holder.adapterPosition].likeOn.id, false)
                        Glide.with(parent)
                            .load(R.drawable.ic_love)
                            .into(binding.commentHeartIc)
                        val newNum = commentList[holder.adapterPosition].likeCount - 1
                        if (newNum == 0) {
                            binding.commentHeartNum.visibility = View.INVISIBLE
                        }else {
                            binding.commentHeartNum.text = "$newNum"
                        }
                    }else {
                        //좋아요 안눌러져 있는 상태
                        CommentService(this).tryPatchCommentLike(commentList[holder.adapterPosition].likeOn.id, true)
                        Glide.with(parent)
                            .load(R.drawable.ic_love_fill)
                            .into(binding.commentHeartIc)
                        val newNum = commentList[holder.adapterPosition].likeCount + 1
                        binding.commentHeartNum.text = "$newNum"
                    }
                }
            }
            binding.commentUploadChild.setOnClickListener {
                val editor = ApplicationClass.sSharedPreferences.edit()
                editor.putInt("groupId", commentList[holder.adapterPosition].commentId)
                editor.apply()

                postCommentsInterface.postComments(commentList[holder.adapterPosition].profileName)
            }
        }
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onGetCommentsSuccess(response: CommentResponse) {
        this.commentChildList = response.result
    }

    override fun onGetCommentsFailure(message: String) {
        Log.d("오류", message)
    }

    override fun onPostCommentLikeSuccess(response: LikeResponse) {
        Log.d("댓글 좋아요", "성공")
    }

    override fun onPostCommentLikeFailure(message: String) {
        Log.d("오류", message)
    }

    interface PostCommentsInterface {
        fun postComments(profileName: String)
    }
}