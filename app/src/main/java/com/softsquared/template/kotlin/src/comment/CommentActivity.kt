package com.softsquared.template.kotlin.src.comment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityCommentBinding
import com.softsquared.template.kotlin.src.comment.adapters.CommentAdapter
import com.softsquared.template.kotlin.src.comment.models.AddCommentRequest
import com.softsquared.template.kotlin.src.comment.models.CommentResponse
import com.softsquared.template.kotlin.src.main.home.models.LikeResponse


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

        binding.commentBtnUpload.setOnClickListener {
            if(binding.commentEt.text != null) {
                val comment = binding.commentEt.text.toString()
                val request = AddCommentRequest(comment, 0, postId)
                CommentService(this).tryPostComments(request)
                hideKeyboard(this)
                binding.commentEt.clearFocus()
                binding.commentEt.text!!.clear()

                //새로고침
                finish() //인텐트 종료
                overridePendingTransition(0, 0) //인텐트 효과 없애기
                val intent = intent //인텐트
                startActivity(intent) //액티비티 열기
                overridePendingTransition(0, 0) //인텐트 효과 없애기
            }
        }
        binding.commentSwipe.setOnRefreshListener {
            //새로고침
            finish() //인텐트 종료
            overridePendingTransition(0, 0) //인텐트 효과 없애기
            val intent = intent //인텐트
            startActivity(intent) //액티비티 열기
            overridePendingTransition(0, 0) //인텐트 효과 없애기

            binding.commentSwipe.isRefreshing = false
        }
    }

    private fun hideKeyboard(activity: Activity){
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.window.decorView.applicationWindowToken, 0)
    }

    override fun onGetCommentsSuccess(response: CommentResponse) {
        binding.commentRv.layoutManager = LinearLayoutManager(this)
        binding.commentRv.adapter = CommentAdapter(response.result)
    }

    override fun onGetCommentsFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onPostCommentLikeSuccess(response: LikeResponse) {
    }

    override fun onPostCommentLikeFailure(message: String) {
    }
}