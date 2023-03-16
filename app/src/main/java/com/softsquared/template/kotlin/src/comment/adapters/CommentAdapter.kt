package com.softsquared.template.kotlin.src.comment.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.R
import com.softsquared.template.kotlin.databinding.ItemCommentBinding
import com.softsquared.template.kotlin.src.comment.CommentFragmentInterface
import com.softsquared.template.kotlin.src.comment.CommentService
import com.softsquared.template.kotlin.src.comment.models.CommentResponse
import com.softsquared.template.kotlin.src.comment.models.ResultComment

class CommentAdapter(private var commentList: List<ResultComment>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(), CommentFragmentInterface {

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
//                CommentService(this).tryGetChildComments(commentList[holder.adapterPosition].commentId)
//                binding.commentChildRv.layoutManager = LinearLayoutManager(parent.context)
//                binding.commentChildRv.adapter = CommentAdapter(commentList)
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
        commentList = response.result
    }

    override fun onGetCommentsFailure(message: String) {
        Log.d("오류", message)
    }
}