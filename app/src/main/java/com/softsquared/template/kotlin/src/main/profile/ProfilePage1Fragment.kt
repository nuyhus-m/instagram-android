package com.softsquared.template.kotlin.src.main.profile

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentProfilePage1Binding
import com.softsquared.template.kotlin.src.main.profile.adpaters.ProfilePostAdapter

class ProfilePage1Fragment : BaseFragment<FragmentProfilePage1Binding>(
    FragmentProfilePage1Binding::bind,
    R.layout.fragment_profile_page1
) {

    private val postList = listOf<Int>(R.drawable.ex_photo)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (postList.isNotEmpty()) {
            binding.profilePage1Zero.visibility = View.GONE
        }
        binding.profilePage1Rv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.profilePage1Rv.adapter = ProfilePostAdapter(postList)
    }
}