package com.softsquared.template.kotlin.src.main.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentFollowerBinding
import com.softsquared.template.kotlin.src.main.list.adapters.FollowerAdapter

class FollowerFragment : BaseFragment<FragmentFollowerBinding>(FragmentFollowerBinding::bind, R.layout.fragment_follower) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.followerRv.layoutManager = LinearLayoutManager(requireContext())
        binding.followerRv.adapter = FollowerAdapter()
    }
}