package com.softsquared.template.kotlin.src.comment

import com.softsquared.template.kotlin.src.comment.models.CommentResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentRetrofitInterface {
    @GET("/app/posts/comment/{post-id}")
    fun getComments(@Path("post-id") postId: Int): Call<CommentResponse>
}