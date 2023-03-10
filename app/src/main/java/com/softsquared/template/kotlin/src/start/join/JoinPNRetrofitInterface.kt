package com.softsquared.template.kotlin.src.start.join

import com.softsquared.template.kotlin.src.start.join.models.JoinPNRequest
import com.softsquared.template.kotlin.src.start.join.models.JoinPNResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JoinPNRetrofitInterface {
    @POST("/app/users/phone")
    fun postJoinPN(@Body params: JoinPNRequest) : Call<JoinPNResponse>
}