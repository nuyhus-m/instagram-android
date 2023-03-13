package com.softsquared.template.kotlin.src.main.list

import com.softsquared.template.kotlin.src.main.list.models.FollowerResponse
import com.softsquared.template.kotlin.src.main.list.models.FollowingResponse

interface FollowFragmentInterface {

    fun onGetFollowersSuccess(response: FollowerResponse)

    fun onGetFollowersFailure(message: String)

    fun onGetFollowingsSuccess(response: FollowingResponse)

    fun onGetFollowingsFailure(message: String)
}