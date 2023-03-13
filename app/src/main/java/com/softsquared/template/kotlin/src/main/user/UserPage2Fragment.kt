package com.softsquared.template.kotlin.src.main.user

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentUserPage2Binding

class UserPage2Fragment : BaseFragment<FragmentUserPage2Binding>(FragmentUserPage2Binding::bind, R.layout.fragment_user_page2) {

    private val postList = listOf<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (postList.isNotEmpty()) {
            binding.userPage2Zero.visibility = View.GONE
        }
    }
}