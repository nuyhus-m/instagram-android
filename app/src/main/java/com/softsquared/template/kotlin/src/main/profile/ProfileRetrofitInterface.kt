package com.softsquared.template.kotlin.src.main.profile

import com.softsquared.template.kotlin.src.main.profile.models.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileRetrofitInterface {
    @GET("/app/users/{user_id}")
    fun getProfile(
        @Path("user_id") userId: Int
    ): Call<ProfileResponse>
}