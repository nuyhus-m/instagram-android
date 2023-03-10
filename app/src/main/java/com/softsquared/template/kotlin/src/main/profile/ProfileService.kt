package com.softsquared.template.kotlin.src.main.profile

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.profile.models.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileService(val profileFragmentInterface: ProfileFragmentInterface) {

    fun tryGetProfile(accessToken: String, userId: Int) {
        val profileRetrofitInterface =
            ApplicationClass.sRetrofit.create(ProfileRetrofitInterface::class.java)
        profileRetrofitInterface.getProfile(accessToken, userId)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    profileFragmentInterface.onGetProfileSuccess(response.body() as ProfileResponse)
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    profileFragmentInterface.onGetProfileFailure(t.message ?: "통신 오류")
                }

            })
    }
}