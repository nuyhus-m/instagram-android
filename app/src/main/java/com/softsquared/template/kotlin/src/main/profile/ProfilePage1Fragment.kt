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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profilePage1Rv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.profilePage1Rv.adapter = ProfilePostAdapter()
    }
}