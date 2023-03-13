package com.softsquared.template.kotlin.src.main.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentUserPage1Binding
import com.softsquared.template.kotlin.src.main.profile.ProfileFragmentInterface
import com.softsquared.template.kotlin.src.main.profile.ProfilePostFragmentInterface
import com.softsquared.template.kotlin.src.main.profile.ProfilePostService
import com.softsquared.template.kotlin.src.main.profile.adpaters.ProfilePostAdapter
import com.softsquared.template.kotlin.src.main.profile.models.ProfilePostResponse
import com.softsquared.template.kotlin.src.main.profile.models.ProfileResponse

class UserPage1Fragment : BaseFragment<FragmentUserPage1Binding>(FragmentUserPage1Binding::bind, R.layout.fragment_user_page1),ProfilePostFragmentInterface {

    private val postList = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = ApplicationClass.sSharedPreferences.getInt("aUserId", -1)
        ProfilePostService(this).tryGetProfilePost(userId)
    }

    override fun onGetProfilePostSuccess(response: ProfilePostResponse) {
        for (i in 0 until response.post.size) {
            postList.add(response.post[i].photos[0].photoUrl)
        }
        if (postList.isNotEmpty()) {
            binding.userPage1Zero.visibility = View.GONE
        }
        binding.userPage1Rv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.userPage1Rv.adapter = ProfilePostAdapter(postList)
    }

    override fun onGetProfilePostFailure(message: String) {
        showCustomToast("오류 : $message")
    }

}