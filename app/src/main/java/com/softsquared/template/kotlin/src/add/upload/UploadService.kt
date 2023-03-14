package com.softsquared.template.kotlin.src.add.upload

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.add.upload.models.UploadRequest
import com.softsquared.template.kotlin.src.add.upload.models.UploadResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class UploadService(val uploadFragmentInterface: UploadFragmentInterface) {

    fun tryUploadPosts(request: UploadRequest) {
        val uploadRetrofitInterface =
            ApplicationClass.sRetrofit.create(UploadRetrofitInterface::class.java)
        uploadRetrofitInterface.uploadPosts(request)
            .enqueue(object : Callback<UploadResponse> {
                override fun onResponse(
                    call: Call<UploadResponse>,
                    response: Response<UploadResponse>
                ) {
                    uploadFragmentInterface.onUploadPostsSuccess(response.body() as UploadResponse)
                }

                override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                    uploadFragmentInterface.onUploadPostsFailure(t.message ?: "통신 오류")
                }

            })
    }
}