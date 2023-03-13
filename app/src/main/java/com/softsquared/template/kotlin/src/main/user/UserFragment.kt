package com.softsquared.template.kotlin.src.main.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentUserBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.profile.ProfileFragmentInterface
import com.softsquared.template.kotlin.src.main.profile.ProfileService
import com.softsquared.template.kotlin.src.main.profile.adpaters.ProfilePagerAdapter
import com.softsquared.template.kotlin.src.main.profile.models.ProfileResponse
import com.softsquared.template.kotlin.src.main.user.adapters.UserPagerAdapter

class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::bind, R.layout.fragment_user), ProfileFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userToolbar.navigationIcon = requireContext().getDrawable(com.softsquared.template.kotlin.R.drawable.ic_back_resize)
        binding.userToolbar.setNavigationOnClickListener {
            val act = activity as MainActivity
            act.fragmentRemoveBackStack(resources.getString(R.string.user_fragment))
        }

        val tabIcon = listOf(R.drawable.ic_profile_tab1, R.drawable.ic_profile_tab2)

        binding.userPages.adapter = UserPagerAdapter(context as FragmentActivity)

        TabLayoutMediator(binding.userTab, binding.userPages) { tab: TabLayout.Tab, i: Int ->
            tab.setIcon(tabIcon[i])
            if (i == 0){
                tab.icon?.setTint(resources.getColor(R.color.black, resources.newTheme()))
            }
            tab.setCustomView(R.layout.custom_tab)
        }.attach()

        binding.userTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(resources.getColor(R.color.black, resources.newTheme()))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(resources.getColor(R.color.grayForTab, resources.newTheme()))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        val userId = ApplicationClass.sSharedPreferences.getInt("aUserId", 1)
        ProfileService(this).tryGetProfile(userId)
    }

    override fun onGetProfileSuccess(response: ProfileResponse) {
            binding.userNickName.text = response.result.nickname
            Glide.with(this)
                .load(response.result.profile_image_url)
                .into(binding.userImg)
            binding.userPostNum.text = response.result.post_count.toString()
            binding.userFollowerNum.text = response.result.follower_count.toString()
            binding.userFollowingNum.text = response.result.following_count.toString()
    }

    override fun onGetProfileFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}