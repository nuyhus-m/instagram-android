package com.softsquared.template.kotlin.src.comment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityCommentBinding
import com.softsquared.template.kotlin.src.comment.adapters.CommentAdapter

class CommentActivity : BaseActivity<ActivityCommentBinding>(ActivityCommentBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.commentToolbar.navigationIcon = this.getDrawable(com.softsquared.template.kotlin.R.drawable.ic_back_resize)
        binding.commentToolbar.setNavigationOnClickListener {
            finish()
        }

        val photo = intent.getStringExtra("photo")
        Glide.with(this)
            .load(photo)
            .into(binding.commentParentPhoto)
        val nickName = intent.getStringExtra("nickName")
        binding.commentParentNickName.text = nickName
        val content = intent.getStringExtra("content")
        binding.commentParentContent.text = content


        binding.commentRv.layoutManager = LinearLayoutManager(this)
        binding.commentRv.adapter = CommentAdapter()
    }
}