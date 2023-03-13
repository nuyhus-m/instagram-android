package com.softsquared.template.kotlin.src.main.user.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.softsquared.template.kotlin.src.main.user.UserPage1Fragment
import com.softsquared.template.kotlin.src.main.user.UserPage2Fragment

private const val NUM_PAGES = 2

class UserPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa){

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UserPage1Fragment()
            else -> UserPage2Fragment()
        }
    }

}