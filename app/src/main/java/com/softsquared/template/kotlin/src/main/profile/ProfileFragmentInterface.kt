package com.softsquared.template.kotlin.src.main.profile

import com.softsquared.template.kotlin.src.main.profile.models.ProfileResponse

interface ProfileFragmentInterface {

    fun onGetProfileSuccess(response: ProfileResponse)

    fun onGetProfileFailure(message: String)
}