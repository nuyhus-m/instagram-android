package com.softsquared.template.kotlin.src.main.user

import android.os.Bundle
import android.view.View
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
import com.softsquared.template.kotlin.src.main.profile.models.ProfileResponse
import com.softsquared.template.kotlin.src.main.user.adapters.UserPagerAdapter
import com.softsquared.template.kotlin.src.main.user.models.FollowResponse
import com.softsquared.template.kotlin.src.main.user.models.UnFollowResponse

class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::bind, R.layout.fragment_user), ProfileFragmentInterface, UserFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userToolbar.navigationIcon = requireContext().getDrawable(com.softsquared.template.kotlin.R.drawable.ic_back_resize)
        binding.userToolbar.setNavigationOnClickListener {
            val act = activity as MainActivity
            if (ApplicationClass.sSharedPreferences.getInt("tabItem",-1) == 2) {
                act.fragmentRemoveBackStack(resources.getString(R.string.user_fragment))
            } else {
                act.fragmentController(resources.getString(R.string.list_fragment), f, f)
            }
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

        val aUserId = ApplicationClass.sSharedPreferences.getInt("aUserId", -1)
        ProfileService(this).tryGetProfile(aUserId)
        binding.userBtnFollowing.setOnClickListener {
            val userId = ApplicationClass.sSharedPreferences.getInt("userId", -1)
            if (binding.userBtnFollowing.text == "팔로잉") {
                UserService(this).tryUnFollow(userId, aUserId)
                val followerNum = binding.userFollowerNum.text.toString().toInt() - 1
                binding.userFollowerNum.text = followerNum.toString()
            } else {
                UserService(this).tryFollow(userId, aUserId)
                val followerNum = binding.userFollowerNum.text.toString().toInt() + 1
                binding.userFollowerNum.text = followerNum.toString()
            }
        }
    }

    override fun onGetProfileSuccess(response: ProfileResponse) {
        binding.userNickName.text = response.result.nickname
        Glide.with(this)
            .load(response.result.profile_image_url)
            .into(binding.userImg)
        binding.userPostNum.text = response.result.post_count.toString()
        binding.userFollowerNum.text = response.result.follower_count.toString()
        binding.userFollowingNum.text = response.result.following_count.toString()
        val followStatus = ApplicationClass.sSharedPreferences.getInt("aUserFollow", -1)
        if (followStatus == 0) {
            binding.userBtnFollowing.text = "팔로우"
            binding.userBtnFollowing.setTextColor(requireContext().getColorStateList(R.color.white))
            binding.userBtnFollowing.background = requireContext().getDrawable(R.drawable.background_btn_blue)
        }
        if(response.result.introduce != null){
            binding.userIntroduce.text = response.result.introduce.toString()
        } else {
            binding.userIntroduce.visibility = View.GONE
        }
        when(response.result.connected_count) {
            0 -> binding.userConnected.visibility = View.GONE
            1 -> {
                Glide.with(requireContext())
                    .load(response.result.connected_friend_profiles[0].profile_image_url)
                    .into(binding.userConnectedPhoto1)
                binding.userConnectedPhoto2.visibility = View.GONE
                binding.userConnectedText.text = "${response.result.connected_friend_profiles[0].nickname}님이 팔로우합니다."
            }
            2 -> {
                Glide.with(requireContext())
                    .load(response.result.connected_friend_profiles[0].profile_image_url)
                    .into(binding.userConnectedPhoto1)
                Glide.with(requireContext())
                    .load(response.result.connected_friend_profiles[1].profile_image_url)
                    .into(binding.userConnectedPhoto2)
                binding.userConnectedText.text =
                    "${response.result.connected_friend_profiles[0].nickname}님, ${response.result.connected_friend_profiles[1].nickname}님이 팔로우합니다."
            }
            else -> {
                Glide.with(requireContext())
                    .load(response.result.connected_friend_profiles[0].profile_image_url)
                    .into(binding.userConnectedPhoto1)
                Glide.with(requireContext())
                    .load(response.result.connected_friend_profiles[1].profile_image_url)
                    .into(binding.userConnectedPhoto2)
                binding.userConnectedText.text =
                    "${response.result.connected_friend_profiles[0].nickname}님, ${response.result.connected_friend_profiles[1].nickname}님 외 ${response.result.connected_count}명이 팔로우합니다."
            }
        }
    }

    override fun onGetProfileFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onUnFollowSuccess(response: UnFollowResponse) {
        if (response.code == 1000) {
            binding.userBtnFollowing.text = "팔로우"
            binding.userBtnFollowing.setTextColor(requireContext().getColorStateList(R.color.white))
            binding.userBtnFollowing.background = requireContext().getDrawable(R.drawable.background_btn_blue)
        }
    }

    override fun onUnFollowFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onFollowSuccess(response: FollowResponse) {
        if (response.code == 1000) {
            binding.userBtnFollowing.text = "팔로잉"
            binding.userBtnFollowing.setTextColor(requireContext().getColorStateList(R.color.black))
            binding.userBtnFollowing.background = requireContext().getDrawable(R.drawable.background_btn_gray)
        }
    }

    override fun onFollowFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}