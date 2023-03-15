package com.softsquared.template.kotlin.src.main.search

import com.softsquared.template.kotlin.src.main.search.models.Search2Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Search2RetrofitInterface {

    @GET("/app/users")
    fun getSearch(
        @Query("user-keyword") userKeyword : String
    ): Call<Search2Response>
}