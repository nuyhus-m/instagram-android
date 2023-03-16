package com.softsquared.template.kotlin.src.comment

import android.util.Log
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.comment.models.AddCommentRequest
import com.softsquared.template.kotlin.src.comment.models.AddCommentResponse
import com.softsquared.template.kotlin.src.comment.models.CommentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class CommentService(val commentFragmentInterface: CommentFragmentInterface) {

    fun tryGetComments(postId: Int) {
        val commentRetrofitInterface =
            ApplicationClass.sRetrofit.create(CommentRetrofitInterface::class.java)
        commentRetrofitInterface.getComments(postId).enqueue(object : Callback<CommentResponse> {
            override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>) {
                commentFragmentInterface.onGetCommentsSuccess(response.body() as CommentResponse)
            }

            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                commentFragmentInterface.onGetCommentsFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetChildComments(parentId: Int) {
        val commentRetrofitInterface =
            ApplicationClass.sRetrofit.create(CommentRetrofitInterface::class.java)
        commentRetrofitInterface.getChildComments(parentId).enqueue(object : Callback<CommentResponse> {
            override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>) {
                val result = response.body() as CommentResponse
                Log.d("답글","메시지: ${result.message}")
                commentFragmentInterface.onGetCommentsSuccess(result)
            }

            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                commentFragmentInterface.onGetCommentsFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostComments(comment: AddCommentRequest) {
        val commentRetrofitInterface =
            ApplicationClass.sRetrofit.create(CommentRetrofitInterface::class.java)
        commentRetrofitInterface.postComments(comment).enqueue(object : Callback<AddCommentResponse> {
            override fun onResponse(
                call: Call<AddCommentResponse>,
                response: Response<AddCommentResponse>
            ) {
                val result = response.body() as AddCommentResponse
                Log.d("댓글달기", result.message.toString())
            }

            override fun onFailure(call: Call<AddCommentResponse>, t: Throwable) {
                Log.d("댓글달기", t.message.toString())
            }
        })
    }
}