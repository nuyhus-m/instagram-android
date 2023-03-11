package com.softsquared.template.kotlin.src.main.home

import com.softsquared.template.kotlin.src.main.home.models.HomePostResponse

interface HomeFragmentInterface {

    fun onGetHomePostSuccess(response: HomePostResponse)

    fun onGetHomePostFailure(message: String)

}