package com.softsquared.template.kotlin.src.add.upload

import com.softsquared.template.kotlin.src.add.upload.models.UploadResponse

interface UploadFragmentInterface {

    fun onUploadPostsSuccess(response: UploadResponse)

    fun onUploadPostsFailure(message: String)
}