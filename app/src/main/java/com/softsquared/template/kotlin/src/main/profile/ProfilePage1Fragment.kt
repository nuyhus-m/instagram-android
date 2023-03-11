package com.softsquared.template.kotlin.src.main.profile

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentProfilePage1Binding
import com.softsquared.template.kotlin.src.main.profile.adpaters.ProfilePostAdapter
import com.softsquared.template.kotlin.src.main.profile.models.ProfilePostResponse

class ProfilePage1Fragment : BaseFragment<FragmentProfilePage1Binding>(
    FragmentProfilePage1Binding::bind,
    R.layout.fragment_profile_page1
), ProfilePostFragmentInterface {

    private val postList = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = ApplicationClass.sSharedPreferences.getInt("userId", -1)
        ProfilePostService(this).tryGetProfilePost(userId)
    }

    override fun onGetProfilePostSuccess(response: ProfilePostResponse) {
        for (i in 0 until response.post.size) {
            postList.add(response.post[i].photos[0].photoUrl)
        }
        if (postList.isNotEmpty()) {
            binding.profilePage1Zero.visibility = View.GONE
        }
        binding.profilePage1Rv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.profilePage1Rv.adapter = ProfilePostAdapter(postList)
    }

    override fun onGetProfilePostFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}