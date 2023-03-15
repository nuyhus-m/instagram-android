package com.softsquared.template.kotlin.src.main.user

import com.softsquared.template.kotlin.src.main.user.models.FollowResponse
import com.softsquared.template.kotlin.src.main.user.models.UnFollowResponse
import retrofit2.Call
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserRetrofitInterface {
    @PATCH("/app/follows/{user-id}")
    fun unFollow(
        @Path("user-id") userId: Int,
        @Query("follow-user-id") followUserId: Int
    ):Call<UnFollowResponse>

    @POST("/app/follows/{user-id}")
    fun follow(
        @Path("user-id") userId: Int,
        @Query("follow-user-id") followUserId: Int
    ):Call<FollowResponse>
}