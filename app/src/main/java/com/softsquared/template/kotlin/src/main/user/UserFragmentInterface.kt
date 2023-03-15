package com.softsquared.template.kotlin.src.main.user

import com.softsquared.template.kotlin.src.main.user.models.UnFollowResponse

interface UserFragmentInterface {

    fun onUnFollowSuccess(response:UnFollowResponse)

    fun onUnFollowFailure(message: String)
}