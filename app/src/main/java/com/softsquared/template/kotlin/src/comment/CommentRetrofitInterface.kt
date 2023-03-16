package com.softsquared.template.kotlin.src.comment

import com.softsquared.template.kotlin.src.comment.models.AddCommentRequest
import com.softsquared.template.kotlin.src.comment.models.AddCommentResponse
import com.softsquared.template.kotlin.src.comment.models.CommentResponse
import retrofit2.Call
import retrofit2.http.*

interface CommentRetrofitInterface {
    @GET("/app/posts/comments/{post-id}")
    fun getComments(@Path("post-id") postId: Int): Call<CommentResponse>

    @GET("/app/posts/comments/big-comment")
    fun getChildComments(@Query("parent-id") parentId: Int): Call<CommentResponse>

    @POST("/app/posts/comments")
    fun postComments(@Body params: AddCommentRequest): Call<AddCommentResponse>
}