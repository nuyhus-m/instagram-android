package com.softsquared.template.kotlin.src.main.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentFollowingBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.list.adapters.FollowingAdapter
import com.softsquared.template.kotlin.src.main.list.models.FollowerResponse
import com.softsquared.template.kotlin.src.main.list.models.FollowingResponse

class FollowingFragment : BaseFragment<FragmentFollowingBinding>(FragmentFollowingBinding::bind, R.layout.fragment_following), FollowFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = ApplicationClass.sSharedPreferences.getInt("userId", -1)
        FollowService(this).tryGetFollowings(userId)
    }

    override fun onGetFollowersSuccess(response: FollowerResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetFollowersFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetFollowingsSuccess(response: FollowingResponse) {
        val act = activity as MainActivity
        binding.followingRv.layoutManager = LinearLayoutManager(requireContext())
        binding.followingRv.adapter = FollowingAdapter(response.result.followings, act)
    }

    override fun onGetFollowingsFailure(message: String) {
        showCustomToast("오류: $message")
    }
}