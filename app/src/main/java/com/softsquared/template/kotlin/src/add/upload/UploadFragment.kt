package com.softsquared.template.kotlin.src.add.upload

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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

class UploadFragment : BaseFragment<FragmentUploadBinding>(FragmentUploadBinding::bind, R.layout.fragment_upload), UploadFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photoUrl = listOf(
            "https://trudylin.s3.ap-northeast-2.amazonaws.com/postPhoto/1.jpg",
            "https://trudylin.s3.ap-northeast-2.amazonaws.com/postPhoto/2.png",
            "https://trudylin.s3.ap-northeast-2.amazonaws.com/postPhoto/3.png",
            "https://trudylin.s3.ap-northeast-2.amazonaws.com/postPhoto/4.png"
        )
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

            val i = (ApplicationClass.sSharedPreferences.getInt("gallery", 0) + 1) % 4 -1


            val content = binding.uploadContent.text.toString()
            val photo = Photo(photoUrl[i], listOf())
            val request = UploadRequest(1,content,1, listOf(photo), listOf(),null)
            UploadService(this).tryUploadPosts(request)

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