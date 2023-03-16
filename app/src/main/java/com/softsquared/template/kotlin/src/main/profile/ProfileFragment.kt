package com.softsquared.template.kotlin.src.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentProfileBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.profile.adpaters.ProfilePagerAdapter
import com.softsquared.template.kotlin.src.main.profile.adpaters.ProfileStoryAdapter
import com.softsquared.template.kotlin.src.main.profile.models.ProfileResponse


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::bind, R.layout.fragment_profile), ProfileFragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabIcon = listOf(R.drawable.ic_profile_tab1, R.drawable.ic_profile_tab2)

        binding.profilePages.adapter = ProfilePagerAdapter(context as FragmentActivity)

        TabLayoutMediator(binding.profileTab, binding.profilePages) { tab: TabLayout.Tab, i: Int ->
            tab.setIcon(tabIcon[i])
            if (i == 0){
                tab.icon?.setTint(resources.getColor(R.color.black, resources.newTheme()))
            }
            tab.setCustomView(R.layout.custom_tab)
        }.attach()

        binding.profileTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(resources.getColor(R.color.black, resources.newTheme()))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(resources.getColor(R.color.grayForTab, resources.newTheme()))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.profileRvStory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.profileRvStory.adapter = ProfileStoryAdapter()

        binding.profileStoryUp.setOnClickListener {
            if (binding.profileStory.visibility == View.VISIBLE) {
                binding.profileStory.visibility = View.GONE
                binding.profileStoryUp.setImageResource(R.drawable.ic_profile_story_down)
            }else{
                binding.profileStory.visibility = View.VISIBLE
                binding.profileStoryUp.setImageResource(R.drawable.ic_profile_story_up)
            }
        }

        val userId = ApplicationClass.sSharedPreferences.getInt("userId", -1)
        ProfileService(this).tryGetProfile(userId)

        val act = activity as MainActivity

        binding.profileFollowerList.setOnClickListener {
            val editor = ApplicationClass.sSharedPreferences.edit()
            editor.putInt("tabItem", 0)
            editor.apply()

            act.fragmentController(resources.getString(R.string.list_fragment), f, f)
        }
        binding.profileFollowingList.setOnClickListener {
            val editor = ApplicationClass.sSharedPreferences.edit()
            editor.putInt("tabItem", 1)
            editor.apply()

            act.fragmentController(resources.getString(R.string.list_fragment), f, f)
        }

        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putInt("a", 0)
        editor.apply()
    }

    override fun onGetProfileSuccess(response: ProfileResponse) {
        binding.profileNickName.text = response.result.nickname
        Glide.with(this)
            .load(response.result.profile_image_url)
            .into(binding.profileImg)
        binding.profilePostNum.text = response.result.post_count.toString()
        binding.profileFollowerNum.text = response.result.follower_count.toString()
        binding.profileFollowingNum.text = response.result.following_count.toString()
        if(response.result.introduce != null){
            binding.profileIntroduce.text = response.result.introduce.toString()
        } else {
            binding.profileIntroduce.visibility = View.GONE
        }
    }

    override fun onGetProfileFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}