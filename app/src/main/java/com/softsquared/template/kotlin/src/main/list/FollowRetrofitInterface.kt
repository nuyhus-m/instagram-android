package com.softsquared.template.kotlin.src.main.list

import com.softsquared.template.kotlin.src.main.list.models.FollowerResponse
import com.softsquared.template.kotlin.src.main.list.models.FollowingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowRetrofitInterface {
    @GET("/app/follows/followers")
    fun getFollowers(@Query("user-id") userId: Int): Call<FollowerResponse>
    @GET("/app/follows/followings")
    fun getFollowings(@Query("user-id") userId: Int): Call<FollowingResponse>
}