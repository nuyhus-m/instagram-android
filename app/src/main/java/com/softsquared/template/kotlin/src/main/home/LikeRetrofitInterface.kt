package com.softsquared.template.kotlin.src.main.home

import com.softsquared.template.kotlin.src.main.home.models.LikeResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface LikeRetrofitInterface {
    @POST("/app/posts/likes/{post-id}")
    fun postLike(@Path("post-id") postId: Int): Call<LikeResponse>
}