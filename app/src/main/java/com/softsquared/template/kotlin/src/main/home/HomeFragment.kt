package com.softsquared.template.kotlin.src.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentHomeBinding
import com.softsquared.template.kotlin.src.main.home.adapters.HomePostAdapter
import com.softsquared.template.kotlin.src.main.home.adapters.HomeStoryAdapter
import com.softsquared.template.kotlin.src.main.home.models.HomePostResponse
import com.softsquared.template.kotlin.src.main.home.models.HomeStoryResponse
import com.softsquared.template.kotlin.src.main.home.models.ResultHomeStory

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home), HomeFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        HomeService(this).tryGetPosts()
        HomeService(this).tryGetStories()
    }

    override fun onGetHomePostSuccess(response: HomePostResponse) {
        if (response.post != null) {
            binding.homeRvPost.layoutManager = LinearLayoutManager(requireContext())
            binding.homeRvPost.adapter = HomePostAdapter(response.post)
        }
    }

    override fun onGetHomePostFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onGetHomeStoriesSuccess(response: HomeStoryResponse) {
        var selfPosition = -1
        for (i in 0 until response.result.size){
            if (response.result[i].self_status == 1) {
                selfPosition = i
                break
            }
        }
        //본인 계정
        val nickName =
            ApplicationClass.sSharedPreferences.getString("profileNickName", "default")!!
        val photo = ApplicationClass.sSharedPreferences.getString("profilePhoto", "##")!!
        val userId = ApplicationClass.sSharedPreferences.getInt("userId", -1)
        val storySelf = ResultHomeStory(nickName, photo, 1, "nothing", userId, 1)
        //정렬
        val storyList: MutableList<ResultHomeStory> = response.result as MutableList<ResultHomeStory>
        if (selfPosition != -1) {
            storyList[0] = response.result[selfPosition]
            storyList[selfPosition] = response.result[0]
        } else {
            storyList.add(0, storySelf)
        }

        binding.homeRvStory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.homeRvStory.adapter = HomeStoryAdapter(storyList)
    }

    override fun onGetHomeStoriesFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}