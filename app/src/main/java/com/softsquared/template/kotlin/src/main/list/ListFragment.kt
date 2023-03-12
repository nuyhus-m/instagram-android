package com.softsquared.template.kotlin.src.main.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentListBinding
import com.softsquared.template.kotlin.src.main.list.adapters.ListPagerAdapter

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::bind, R.layout.fragment_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listToolbar.navigationIcon = requireContext().getDrawable(com.softsquared.template.kotlin.R.drawable.ic_back_resize)
        binding.listToolbar.setNavigationOnClickListener {

        }

        val tabTitle = listOf(
            resources.getString(R.string.list_tab_follower),
            resources.getString(R.string.list_tab_following)
        )

        binding.listViewPager.adapter = ListPagerAdapter(context as FragmentActivity)
        TabLayoutMediator(binding.listTabLayout, binding.listViewPager){ tab: TabLayout.Tab, i: Int ->
            tab.text = tabTitle[i]
        }.attach()
    }
}