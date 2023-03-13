package com.softsquared.template.kotlin.src.main.list

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.list.models.FollowerResponse
import com.softsquared.template.kotlin.src.main.list.models.FollowingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class FollowService(val followFragmentInterface: FollowFragmentInterface) {

    fun tryGetFollowers(userId: Int) {
        val followerRetrofitInterface =
            ApplicationClass.sRetrofit.create(FollowRetrofitInterface::class.java)
        followerRetrofitInterface.getFollowers(userId).enqueue(object : Callback<FollowerResponse>{
            override fun onResponse(
                call: Call<FollowerResponse>,
                response: Response<FollowerResponse>
            ) {
                followFragmentInterface.onGetFollowersSuccess(response.body() as FollowerResponse)
            }

            override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {
                followFragmentInterface.onGetFollowersFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryGetFollowings(userId: Int) {
        val followingRetrofitInterface =
            ApplicationClass.sRetrofit.create(FollowRetrofitInterface::class.java)
        followingRetrofitInterface.getFollowings(userId).enqueue(object : Callback<FollowingResponse>{
            override fun onResponse(
                call: Call<FollowingResponse>,
                response: Response<FollowingResponse>
            ) {
                followFragmentInterface.onGetFollowingsSuccess(response.body() as FollowingResponse)
            }

            override fun onFailure(call: Call<FollowingResponse>, t: Throwable) {
                followFragmentInterface.onGetFollowingsFailure(t.message ?: "통신 오류")
            }

        })
    }
}