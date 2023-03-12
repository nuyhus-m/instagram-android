package com.softsquared.template.kotlin.src.main.list

import com.softsquared.template.kotlin.src.main.list.models.FollowerResponse

interface FollowerFragmentInterface {

    fun onGetFollowersSuccess(response: FollowerResponse)

    fun onGetFollowersFailure(message: String)
}