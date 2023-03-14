package com.softsquared.template.kotlin.src.add.upload

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentUploadBinding
import com.softsquared.template.kotlin.src.add.AddActivity
import com.softsquared.template.kotlin.src.add.upload.models.Photo
import com.softsquared.template.kotlin.src.add.upload.models.UploadRequest
import com.softsquared.template.kotlin.src.add.upload.models.UploadResponse
import com.softsquared.template.kotlin.src.main.MainActivity
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class UploadFragment : BaseFragment<FragmentUploadBinding>(FragmentUploadBinding::bind, R.layout.fragment_upload), UploadFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = activity as AddActivity

        binding.uploadToolbar.navigationIcon = requireContext().getDrawable(R.drawable.ic_back_resize)
        binding.uploadToolbar.setNavigationOnClickListener {
            act.fragmentRemoveBackStack(resources.getString(R.string.upload_fragment))
        }
        val uri = ApplicationClass.sSharedPreferences.getString("uri", null)!!
        Glide.with(requireContext())
            .load(uri)
            .into(binding.uploadPhoto)

        binding.uploadToolbarBtnCheck.setOnClickListener {
//            Log.d("up", uri)
//            val file = File(uri)
//            val requestFile = file.asRequestBody("image/png".toMediaTypeOrNull())
//            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
//            val photo = Photo(body, listOf())
//            val request = UploadRequest(1, "안녕하세요", 1, listOf(photo), listOf())
//            UploadService(this).tryUploadPosts(request)

            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onUploadPostsSuccess(response: UploadResponse) {
        Log.d("up","${response.message}")
    }

    override fun onUploadPostsFailure(message: String) {
        showCustomToast("오류: $message")
        Log.d("up","오류: $message")
    }
}