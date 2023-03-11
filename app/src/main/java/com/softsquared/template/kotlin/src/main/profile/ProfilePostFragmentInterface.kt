package com.softsquared.template.kotlin.src.main.profile

import com.softsquared.template.kotlin.src.main.profile.models.ProfilePostResponse

interface ProfilePostFragmentInterface {

    fun onGetProfilePostSuccess(response: ProfilePostResponse)

    fun onGetProfilePostFailure(message: String)
}