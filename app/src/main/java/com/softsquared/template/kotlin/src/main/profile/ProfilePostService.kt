package com.softsquared.template.kotlin.src.main.profile

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.profile.models.ProfilePostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilePostService(val profilePostFragmentInterface: ProfilePostFragmentInterface) {

    fun tryGetProfilePost(userId: Int){
        val profilePostRetrofitInterface =
            ApplicationClass.sRetrofit.create(ProfilePostRetrofitInterface::class.java)
        profilePostRetrofitInterface.getProfilePost(userId)
            .enqueue(object : Callback<ProfilePostResponse> {
                override fun onResponse(
                    call: Call<ProfilePostResponse>,
                    response: Response<ProfilePostResponse>
                ) {
                    profilePostFragmentInterface.onGetProfilePostSuccess(response.body() as ProfilePostResponse)
                }

                override fun onFailure(call: Call<ProfilePostResponse>, t: Throwable) {
                    profilePostFragmentInterface.onGetProfilePostFailure(t.message ?: "통신 오류")
                }

            })
    }
}