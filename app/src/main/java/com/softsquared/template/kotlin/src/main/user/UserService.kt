package com.softsquared.template.kotlin.src.main.user

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.user.models.FollowResponse
import com.softsquared.template.kotlin.src.main.user.models.UnFollowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserService(val userFragmentInterface: UserFragmentInterface) {

    fun tryUnFollow(userId:Int, followUserId:Int) {
        val userRetrofitInterface =
            ApplicationClass.sRetrofit.create(UserRetrofitInterface::class.java)
        userRetrofitInterface.unFollow(userId, followUserId).enqueue(object : Callback<UnFollowResponse>{
            override fun onResponse(
                call: Call<UnFollowResponse>,
                response: Response<UnFollowResponse>
            ) {
                userFragmentInterface.onUnFollowSuccess(response.body() as UnFollowResponse)
            }

            override fun onFailure(call: Call<UnFollowResponse>, t: Throwable) {
                userFragmentInterface.onUnFollowFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryFollow(userId:Int, followUserId:Int) {
        val userRetrofitInterface =
            ApplicationClass.sRetrofit.create(UserRetrofitInterface::class.java)
        userRetrofitInterface.follow(userId, followUserId).enqueue(object : Callback<FollowResponse>{
            override fun onResponse(
                call: Call<FollowResponse>,
                response: Response<FollowResponse>
            ) {
                userFragmentInterface.onFollowSuccess(response.body() as FollowResponse)
            }

            override fun onFailure(call: Call<FollowResponse>, t: Throwable) {
                userFragmentInterface.onFollowFailure(t.message ?: "통신 오류")
            }

        })
    }
}