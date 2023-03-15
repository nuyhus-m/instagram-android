package com.softsquared.template.kotlin.src.main.search

import com.softsquared.template.kotlin.src.main.search.models.Search2Response

interface Search2FragmentInterface {

    fun onGetSearchSuccess(response: Search2Response)

    fun onGetSearchFailure(message: String)
}