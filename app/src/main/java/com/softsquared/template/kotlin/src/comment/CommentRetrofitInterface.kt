package com.softsquared.template.kotlin.src.comment

import com.softsquared.template.kotlin.src.comment.models.AddCommentRequest
import com.softsquared.template.kotlin.src.comment.models.AddCommentResponse
import com.softsquared.template.kotlin.src.comment.models.CommentResponse
import com.softsquared.template.kotlin.src.main.home.models.LikeResponse
import retrofit2.Call
import retrofit2.http.*

interface CommentRetrofitInterface {
    @GET("/app/posts/comments/{post-id}")
    fun getComments(@Path("post-id") postId: Int): Call<CommentResponse>

    @GET("/app/posts/comments/big-comment")
    fun getChildComments(@Query("parent-id") parentId: Int): Call<CommentResponse>

    @POST("/app/posts/comments")
    fun postComments(@Body params: AddCommentRequest): Call<AddCommentResponse>

    @POST("/app/posts/comments/like-status/{comment-id}")
    fun postCommentLike(@Path("comment-id") commentId: Int): Call<LikeResponse>

    @PATCH("/app/posts/comments/like-status/{like-id}/{status}")
    fun patchCommentLike(
        @Path("like-id") likeId: Int,
        @Path("status") status: Boolean
    ): Call<LikeResponse>
}