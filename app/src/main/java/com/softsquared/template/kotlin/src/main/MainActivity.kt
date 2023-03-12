package com.softsquared.template.kotlin.src.main

import android.os.Bundle
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.list.ListFragment
import com.softsquared.template.kotlin.src.main.profile.ProfileFragment
import com.softsquared.template.kotlin.src.main.profile.ProfileFragmentInterface
import com.softsquared.template.kotlin.src.main.profile.ProfileService
import com.softsquared.template.kotlin.src.main.profile.models.ProfileResponse

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), ProfileFragmentInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBtmNav.itemIconTintList = null

        val userId = ApplicationClass.sSharedPreferences.getInt("userId", -1)
        ProfileService(this).tryGetProfile(userId)

        binding.mainBtmNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ListFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_main_btm_nav_search -> {

                    }
                    R.id.menu_main_btm_nav_plus -> {

                    }
                    R.id.menu_main_btm_nav_reels -> {

                    }
                    R.id.menu_main_btm_nav_profile -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, ProfileFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.menu_main_btm_nav_home
        }
    }

    override fun onGetProfileSuccess(response: ProfileResponse) {
        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putString("profilePhoto", response.result.profile_image_url)
        editor.apply()

        Glide.with(this)
            .load(response.result.profile_image_url)
            .into(binding.mainBtmProfileImg)
    }

    override fun onGetProfileFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}