package com.softsquared.template.kotlin.src.main.home

import com.softsquared.template.kotlin.src.main.home.models.HomePostResponse
import com.softsquared.template.kotlin.src.main.home.models.HomeStoryResponse
import retrofit2.Call
import retrofit2.http.*

interface HomeRetrofitInterface {
    @GET("/app/posts/followings")
    fun getPosts(): Call<HomePostResponse>

    @GET("/app/stories/followings")
    fun getStories(): Call<HomeStoryResponse>
}
