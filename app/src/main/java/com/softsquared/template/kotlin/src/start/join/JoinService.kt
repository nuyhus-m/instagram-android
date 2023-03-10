package com.softsquared.template.kotlin.src.start.join

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.start.join.models.JoinEmailRequest
import com.softsquared.template.kotlin.src.start.join.models.JoinPNRequest
import com.softsquared.template.kotlin.src.start.join.models.JoinResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class JoinService(val joinInterface: JoinInterface) {

    fun tryPostJoinPN(joinPNRequest: JoinPNRequest) {
        val joinRetrofitInterface =
            ApplicationClass.sRetrofit.create(JoinRetrofitInterface::class.java)
        joinRetrofitInterface.postJoinPN(joinPNRequest)
            .enqueue(object : Callback<JoinResponse>{
                override fun onResponse(
                    call: Call<JoinResponse>,
                    response: Response<JoinResponse>
                ) {
                    joinInterface.onPostJoinSuccess(response.body() as JoinResponse)
                }

                override fun onFailure(call: Call<JoinResponse>, t: Throwable) {
                    joinInterface.onPostJoinFailure(t.message ?: "통신 오류")
                }
            })
    }

    fun tryPostJoinEmail(joinEmailRequest: JoinEmailRequest) {
        val joinRetrofitInterface =
            ApplicationClass.sRetrofit.create(JoinRetrofitInterface::class.java)
        joinRetrofitInterface.postJoinEmail(joinEmailRequest)
            .enqueue(object : Callback<JoinResponse>{
                override fun onResponse(
                    call: Call<JoinResponse>,
                    response: Response<JoinResponse>
                ) {
                    joinInterface.onPostJoinSuccess(response.body() as JoinResponse)
                }

                override fun onFailure(call: Call<JoinResponse>, t: Throwable) {
                    joinInterface.onPostJoinFailure(t.message ?: "통신 오류")
                }
            })
    }
}