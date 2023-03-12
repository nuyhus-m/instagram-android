package com.softsquared.template.kotlin.src.main.list

import com.softsquared.template.kotlin.src.main.list.models.FollowerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerRetrofitInterface {
    @GET("/app/follows/followers")
    fun getFollowers(@Query("user-id") userId: Int): Call<FollowerResponse>
}