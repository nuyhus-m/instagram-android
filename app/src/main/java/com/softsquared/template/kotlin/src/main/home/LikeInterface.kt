package com.softsquared.template.kotlin.src.main.home

import com.softsquared.template.kotlin.src.main.home.models.LikeResponse

interface LikeInterface {

    fun onPostLikeSuccess(response: LikeResponse)

    fun onPostLikeFailure(message: String)
}