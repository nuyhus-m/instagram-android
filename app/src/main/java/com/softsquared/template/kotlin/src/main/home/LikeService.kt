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

    fun tryDeleteLike(likeId: Int) {
        val likeRetrofitInterface =
            ApplicationClass.sRetrofit.create(LikeRetrofitInterface::class.java)
        likeRetrofitInterface.deleteLike(likeId, false).enqueue(object : Callback<LikeResponse>{
            override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {
                likeInterface.onPostLikeSuccess(response.body() as LikeResponse)
            }

            override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                likeInterface.onPostLikeFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryPostScrap(postId: Int) {
        val likeRetrofitInterface =
            ApplicationClass.sRetrofit.create(LikeRetrofitInterface::class.java)
        likeRetrofitInterface.postScrap(postId).enqueue(object : Callback<LikeResponse>{
            override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {
                likeInterface.onPostLikeSuccess(response.body() as LikeResponse)
            }

            override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                likeInterface.onPostLikeFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryDeleteScrap(scrapId: Int) {
        val likeRetrofitInterface =
            ApplicationClass.sRetrofit.create(LikeRetrofitInterface::class.java)
        likeRetrofitInterface.deleteScrap(scrapId, false).enqueue(object : Callback<LikeResponse>{
            override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {
                likeInterface.onPostLikeSuccess(response.body() as LikeResponse)
            }

            override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                likeInterface.onPostLikeFailure(t.message ?: "통신 오류")
            }

        })
    }
}