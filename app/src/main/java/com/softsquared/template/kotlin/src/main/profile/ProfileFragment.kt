package com.softsquared.template.kotlin.src.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentProfileBinding
import com.softsquared.template.kotlin.src.main.profile.adpaters.ProfilePagerAdapter
import com.softsquared.template.kotlin.src.main.profile.adpaters.ProfileStoryAdapter


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::bind, R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabIcon = listOf(R.drawable.ic_profile_tab1, R.drawable.ic_profile_tab2)

        binding.profilePages.adapter = ProfilePagerAdapter(context as FragmentActivity)

        TabLayoutMediator(binding.profileTab, binding.profilePages) { tab: TabLayout.Tab, i: Int ->
            tab.setIcon(tabIcon[i])
            tab.setCustomView(R.layout.custom_tab)
        }.attach()

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
    }
}