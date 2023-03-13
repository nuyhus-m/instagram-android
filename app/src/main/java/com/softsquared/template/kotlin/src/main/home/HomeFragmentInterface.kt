package com.softsquared.template.kotlin.src.main.home

import com.softsquared.template.kotlin.src.main.home.models.HomePostResponse
import com.softsquared.template.kotlin.src.main.home.models.HomeStoryResponse

interface HomeFragmentInterface {

    fun onGetHomePostSuccess(response: HomePostResponse)

    fun onGetHomePostFailure(message: String)

    fun onGetHomeStoriesSuccess(response: HomeStoryResponse)

    fun onGetHomeStoriesFailure(message: String)
}