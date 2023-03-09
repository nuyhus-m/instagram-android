package com.softsquared.template.kotlin.src.comment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.commentRv.layoutManager = LinearLayoutManager(this)
        binding.commentRv.adapter = CommentAdapter()
    }
}