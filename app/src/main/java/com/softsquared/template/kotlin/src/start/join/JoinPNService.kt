package com.softsquared.template.kotlin.src.start.join

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.start.join.models.JoinPNRequest
import com.softsquared.template.kotlin.src.start.join.models.JoinPNResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class JoinPNService(val joinPNInterface: JoinPNInterface) {

    fun tryPostJoinPN(joinPNRequest: JoinPNRequest) {
        val joinPNRetrofitInterface =
            ApplicationClass.sRetrofit.create(JoinPNRetrofitInterface::class.java)
        joinPNRetrofitInterface.postJoinPN(joinPNRequest)
            .enqueue(object : Callback<JoinPNResponse>{
                override fun onResponse(
                    call: Call<JoinPNResponse>,
                    response: Response<JoinPNResponse>
                ) {
                    joinPNInterface.onPostJoinPNSuccess(response.body() as JoinPNResponse)
                }

                override fun onFailure(call: Call<JoinPNResponse>, t: Throwable) {
                    joinPNInterface.onPostJoinPNFailure(t.message ?: "통신 오류")
                }
            })
    }
}