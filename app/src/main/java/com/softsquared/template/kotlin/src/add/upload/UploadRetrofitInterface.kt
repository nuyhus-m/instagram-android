package com.softsquared.template.kotlin.src.add.upload

import com.softsquared.template.kotlin.src.add.upload.models.UploadRequest
import com.softsquared.template.kotlin.src.add.upload.models.UploadResponse
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadRetrofitInterface {
    @Multipart
    @POST("/app/posts")
    fun uploadPosts(
        @Part post: UploadRequest

    ): Call<UploadResponse>
}