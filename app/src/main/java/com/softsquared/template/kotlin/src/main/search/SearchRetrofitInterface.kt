package com.softsquared.template.kotlin.src.main.search

import com.softsquared.template.kotlin.src.main.search.models.SearchResponse
import retrofit2.Call
import retrofit2.http.GET

interface SearchRetrofitInterface {

    @GET("/app/posts/recommended")
    fun getAllPosts(): Call<SearchResponse>
}