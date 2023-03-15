package com.softsquared.template.kotlin.src.main.search

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.search.models.Search2Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Search2Service(val search2FragmentInterface: Search2FragmentInterface) {

    fun tryGetSearch(userKeyword: String) {
        val searchRetrofitInterface =
            ApplicationClass.sRetrofit.create(Search2RetrofitInterface::class.java)
        searchRetrofitInterface.getSearch(userKeyword).enqueue(object : Callback<Search2Response> {
            override fun onResponse(
                call: Call<Search2Response>,
                response: Response<Search2Response>
            ) {
                search2FragmentInterface.onGetSearchSuccess(response.body() as Search2Response)
            }

            override fun onFailure(call: Call<Search2Response>, t: Throwable) {
                search2FragmentInterface.onGetSearchFailure(t.message ?: "통신 오류")
            }

        })
    }
}