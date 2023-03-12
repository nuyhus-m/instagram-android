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
import com.softsquared.template.kotlin.databinding.FragmentFollowerBinding
import com.softsquared.template.kotlin.src.main.list.adapters.FollowerAdapter
import com.softsquared.template.kotlin.src.main.list.models.FollowerResponse

class FollowerFragment : BaseFragment<FragmentFollowerBinding>(FragmentFollowerBinding::bind, R.layout.fragment_follower), FollowerFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = ApplicationClass.sSharedPreferences.getInt("userId", -1)
        FollowerService(this).tryGetFollowers(userId)
    }

    override fun onGetFollowersSuccess(response: FollowerResponse) {
        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putInt("followerCount", response.result.follower_count)
        editor.apply()

        binding.followerRv.layoutManager = LinearLayoutManager(requireContext())
        binding.followerRv.adapter = FollowerAdapter(response.result.followers)
    }

    override fun onGetFollowersFailure(message: String) {
        TODO("Not yet implemented")
    }
}