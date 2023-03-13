package com.softsquared.template.kotlin.src.main.home

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.home.models.HomePostResponse
import com.softsquared.template.kotlin.src.main.home.models.HomeStoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

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

    fun tryGetStories() {
        val homeRetrofitInterface =
            ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getStories().enqueue(object : Callback<HomeStoryResponse>{
            override fun onResponse(
                call: Call<HomeStoryResponse>,
                response: Response<HomeStoryResponse>
            ) {
                homeFragmentInterface.onGetHomeStoriesSuccess(response.body() as HomeStoryResponse)
            }

            override fun onFailure(call: Call<HomeStoryResponse>, t: Throwable) {
                homeFragmentInterface.onGetHomeStoriesFailure(t.message ?: "통신 오류")
            }

        })
    }
}
