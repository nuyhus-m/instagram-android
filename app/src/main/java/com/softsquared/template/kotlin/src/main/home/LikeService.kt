package com.softsquared.template.kotlin.src.main.home

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.home.models.LikeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LikeService(val likeInterface: LikeInterface) {

    fun tryPostLike(postId: Int) {
        val likeRetrofitInterface =
            ApplicationClass.sRetrofit.create(LikeRetrofitInterface::class.java)
        likeRetrofitInterface.postLike(postId).enqueue(object : Callback<LikeResponse>{
            override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {
                likeInterface.onPostLikeSuccess(response.body() as LikeResponse)
            }

            override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                likeInterface.onPostLikeFailure(t.message ?: "통신 오류")
            }

        })
    }
}