package com.softsquared.template.kotlin.src.main.profile.adpaters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.softsquared.template.kotlin.src.main.profile.ProfilePage1Fragment
import com.softsquared.template.kotlin.src.main.profile.ProfilePage2Fragment

private const val NUM_PAGES = 2

class ProfilePagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa){

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfilePage1Fragment()
            else -> ProfilePage2Fragment()
        }
    }

}