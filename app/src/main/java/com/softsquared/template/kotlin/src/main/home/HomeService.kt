package com.softsquared.template.kotlin.src.main.home

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.home.models.HomePostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val homeFragmentInterface: HomeFragmentInterface) {

    fun tryGetPosts() {
        val homeRetrofitInterface =
            ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getPosts().enqueue(object : Callback<HomePostResponse> {
            override fun onResponse(call: Call<HomePostResponse>, response: Response<HomePostResponse>) {
                homeFragmentInterface.onGetHomePostSuccess(response.body() as HomePostResponse)
            }

            override fun onFailure(call: Call<HomePostResponse>, t: Throwable) {
                homeFragmentInterface.onGetHomePostFailure(t.message ?: "통신 오류")
            }
        })
    }

}
