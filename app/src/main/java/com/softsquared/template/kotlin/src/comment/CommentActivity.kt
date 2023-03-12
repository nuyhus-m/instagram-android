package com.softsquared.template.kotlin.src.comment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityCommentBinding
import com.softsquared.template.kotlin.src.comment.adapters.CommentAdapter
import com.softsquared.template.kotlin.src.comment.models.CommentResponse

class CommentActivity : BaseActivity<ActivityCommentBinding>(ActivityCommentBinding::inflate), CommentFragmentInterface {
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
        val postId = intent.getIntExtra("postId", -1)
        CommentService(this).tryGetComments(postId)

        val profilePhoto = ApplicationClass.sSharedPreferences.getString("profilePhoto", null)
        Glide.with(this)
            .load(profilePhoto)
            .into(binding.commentInputPhoto)
    }

    override fun onGetCommentsSuccess(response: CommentResponse) {
        binding.commentRv.layoutManager = LinearLayoutManager(this)
        binding.commentRv.adapter = CommentAdapter(response.result)
    }

    override fun onGetCommentsFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}