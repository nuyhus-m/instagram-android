package com.softsquared.template.kotlin.src.main.home

import com.softsquared.template.kotlin.src.main.home.models.LikeResponse
import retrofit2.Call
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface LikeRetrofitInterface {
    @POST("/app/posts/likes/{post-id}")
    fun postLike(@Path("post-id") postId: Int): Call<LikeResponse>

    @PATCH("/app/posts/likes/{like-id}/{status}")
    fun deleteLike(
        @Path("like-id") likeId: Int,
        @Path("status") status: Boolean
    ): Call<LikeResponse>

    @POST("/app/posts/scraped/{post-id}")
    fun postScrap(@Path("post-id") postId: Int): Call<LikeResponse>

    @PATCH("/app/posts/scraped/{scrap-id}/{status}")
    fun deleteScrap(
        @Path("scrap-id") scrapId: Int,
        @Path("status") status: Boolean
    ): Call<LikeResponse>
}