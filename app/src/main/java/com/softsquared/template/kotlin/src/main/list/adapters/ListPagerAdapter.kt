package com.softsquared.template.kotlin.src.main.list.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.softsquared.template.kotlin.src.main.list.FollowerFragment
import com.softsquared.template.kotlin.src.main.list.FollowingFragment

private const val NUM_PAGES = 2

class ListPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FollowerFragment()
            else -> FollowingFragment()
        }
    }
}