package com.softsquared.template.kotlin.src.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentListBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.list.adapters.ListPagerAdapter
import com.softsquared.template.kotlin.src.main.list.models.FollowerResponse
import com.softsquared.template.kotlin.src.main.list.models.FollowingResponse

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::bind, R.layout.fragment_list), FollowFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listToolbar.navigationIcon = requireContext().getDrawable(com.softsquared.template.kotlin.R.drawable.ic_back_resize)
        binding.listToolbar.setNavigationOnClickListener {
            val act = activity as MainActivity
            act.fragmentRemoveBackStack(resources.getString(R.string.list_fragment))
        }
        val userId = ApplicationClass.sSharedPreferences.getInt("userId", -1)
        FollowService(this).tryGetFollowers(userId)
    }

    override fun onGetFollowersSuccess(response: FollowerResponse) {
        val tabTitle = listOf(
            resources.getString(R.string.list_tab_follower, response.result.follower_count),
            resources.getString(R.string.list_tab_following, response.result.following_count)
        )

        binding.listViewPager.adapter = ListPagerAdapter(context as FragmentActivity)
        TabLayoutMediator(binding.listTabLayout, binding.listViewPager){ tab: TabLayout.Tab, i: Int ->
            tab.text = tabTitle[i]
        }.attach()
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