package com.softsquared.template.kotlin.src.main.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentFollowingBinding
import com.softsquared.template.kotlin.src.main.list.adapters.FollowingAdapter

class FollowingFragment : BaseFragment<FragmentFollowingBinding>(FragmentFollowingBinding::bind, R.layout.fragment_following) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.followingRv.layoutManager = LinearLayoutManager(requireContext())
        binding.followingRv.adapter = FollowingAdapter()
    }
}