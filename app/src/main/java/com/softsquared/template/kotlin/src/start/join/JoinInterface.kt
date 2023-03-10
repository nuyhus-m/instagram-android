package com.softsquared.template.kotlin.src.start.join

import com.softsquared.template.kotlin.src.start.join.models.JoinResponse

interface JoinInterface {

    fun onPostJoinSuccess(response: JoinResponse)

    fun onPostJoinFailure(message: String)
}