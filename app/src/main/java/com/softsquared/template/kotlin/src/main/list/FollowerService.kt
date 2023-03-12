package com.softsquared.template.kotlin.src.main.list

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.list.models.FollowerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class FollowerService(val followerFragmentInterface: FollowerFragmentInterface) {

    fun tryGetFollowers(userId: Int) {
        val followerRetrofitInterface =
            ApplicationClass.sRetrofit.create(FollowerRetrofitInterface::class.java)
        followerRetrofitInterface.getFollowers(userId).enqueue(object : Callback<FollowerResponse>{
            override fun onResponse(
                call: Call<FollowerResponse>,
                response: Response<FollowerResponse>
            ) {
                followerFragmentInterface.onGetFollowersSuccess(response.body() as FollowerResponse)
            }

            override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {
                followerFragmentInterface.onGetFollowersFailure(t.message ?: "통신 오류")
            }

        })
    }
}