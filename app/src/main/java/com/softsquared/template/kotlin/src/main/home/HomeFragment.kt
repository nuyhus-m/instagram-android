package com.softsquared.template.kotlin.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentHomeBinding
import com.softsquared.template.kotlin.src.main.home.adapters.HomePostAdapter
import com.softsquared.template.kotlin.src.main.home.adapters.HomeStoryAdapter
import com.softsquared.template.kotlin.src.main.home.models.HomePostResponse
import com.softsquared.template.kotlin.src.main.home.models.ResultHomePost

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home), HomeFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeRvStory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.homeRvStory.adapter = HomeStoryAdapter()
    }

    override fun onResume() {
        super.onResume()
        HomeService(this).tryGetPosts()
    }

    override fun onGetHomePostSuccess(response: HomePostResponse) {
        binding.homeRvPost.layoutManager = LinearLayoutManager(requireContext())
        binding.homeRvPost.adapter = HomePostAdapter(response.post)
    }

    override fun onGetHomePostFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}