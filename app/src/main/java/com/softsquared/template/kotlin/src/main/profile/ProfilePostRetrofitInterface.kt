package com.softsquared.template.kotlin.src.main.profile

import com.softsquared.template.kotlin.src.main.profile.models.ProfilePostResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfilePostRetrofitInterface {
    @GET("/app/posts/profiles/user")
    fun getProfilePost(
        @Query("user-id") userId: Int
    ):Call<ProfilePostResponse>
}