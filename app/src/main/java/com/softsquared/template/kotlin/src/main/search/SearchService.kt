package com.softsquared.template.kotlin.src.main.search

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.search.models.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchService(val searchFragmentInterface: SearchFragmentInterface) {

    fun tryGetAllPosts() {
        val searchRetrofitInterface =
            ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java)
        searchRetrofitInterface.getAllPosts()
            .enqueue(object : Callback<SearchResponse>{
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    searchFragmentInterface.onGetAllPostsSuccess(response.body() as SearchResponse)
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    searchFragmentInterface.onGetAllPostsFailure(t.message ?: "통신 오류")
                }

            })
    }
}