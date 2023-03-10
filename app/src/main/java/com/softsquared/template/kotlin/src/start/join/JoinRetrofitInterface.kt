package com.softsquared.template.kotlin.src.start.join

import com.softsquared.template.kotlin.src.start.join.models.JoinEmailRequest
import com.softsquared.template.kotlin.src.start.join.models.JoinPNRequest
import com.softsquared.template.kotlin.src.start.join.models.JoinResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JoinRetrofitInterface {
    @POST("/app/users/phone")
    fun postJoinPN(@Body params: JoinPNRequest) : Call<JoinResponse>

    @POST("/app/users/email")
    fun postJoinEmail(@Body params: JoinEmailRequest) : Call<JoinResponse>
}