package com.softsquared.template.kotlin.src.main.search

import com.softsquared.template.kotlin.src.main.search.models.SearchResponse

interface SearchFragmentInterface {

    fun onGetAllPostsSuccess(response: SearchResponse)

    fun onGetAllPostsFailure(message: String)
}