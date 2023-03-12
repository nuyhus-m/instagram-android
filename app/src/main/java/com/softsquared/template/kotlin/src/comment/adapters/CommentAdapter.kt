package com.softsquared.template.kotlin.src.comment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.databinding.ItemCommentBinding
import com.softsquared.template.kotlin.src.comment.models.ResultComment

class CommentAdapter(val commentList: List<ResultComment>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(private val commentItemBinding: ItemCommentBinding)
        :RecyclerView.ViewHolder(commentItemBinding.root){
            fun bind(comment: ResultComment) {
                Glide.with(itemView)
                    .load(comment.profilePicture)
                    .into(commentItemBinding.commentPhoto)
                commentItemBinding.commentProfileName.text = comment.profileName
                commentItemBinding.commentContent.text = comment.comment
                if (comment.likeCount != 0) {
                    commentItemBinding.commentHeartNum.text = comment.likeCount.toString()
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
}