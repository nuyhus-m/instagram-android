package com.softsquared.template.kotlin.src.start.join

import com.softsquared.template.kotlin.src.start.join.models.JoinPNResponse

interface JoinPNInterface {

    fun onPostJoinPNSuccess(response: JoinPNResponse)

    fun onPostJoinPNFailure(message: String)
}