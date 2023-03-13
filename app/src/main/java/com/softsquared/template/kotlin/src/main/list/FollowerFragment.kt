package com.softsquared.template.kotlin.src.main.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentFollowerBinding
import com.softsquared.template.kotlin.src.main.list.adapters.FollowerAdapter
import com.softsquared.template.kotlin.src.main.list.models.FollowerResponse
import com.softsquared.template.kotlin.src.main.list.models.FollowingResponse

class FollowerFragment : BaseFragment<FragmentFollowerBinding>(FragmentFollowerBinding::bind, R.layout.fragment_follower), FollowFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = ApplicationClass.sSharedPreferences.getInt("userId", -1)
        FollowService(this).tryGetFollowers(userId)
    }

    override fun onGetFollowersSuccess(response: FollowerResponse) {
        binding.followerRv.layoutManager = LinearLayoutManager(requireContext())
        binding.followerRv.adapter = FollowerAdapter(response.result.followers)
    }

    override fun onGetFollowersFailure(message: String) {
        showCustomToast("오류: $message")
    }

    override fun onGetFollowingsSuccess(response: FollowingResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetFollowingsFailure(message: String) {
        TODO("Not yet implemented")
    }
}