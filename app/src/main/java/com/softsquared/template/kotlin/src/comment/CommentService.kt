package com.softsquared.template.kotlin.src.comment

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.comment.models.CommentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                commentFragmentInterface.onGetCommentsSuccess(response.body() as CommentResponse)
            }

            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                commentFragmentInterface.onGetCommentsFailure(t.message ?: "통신 오류")
            }
        })
    }
}