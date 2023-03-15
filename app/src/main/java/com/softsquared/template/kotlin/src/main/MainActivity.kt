package com.softsquared.template.kotlin.src.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.src.add.AddActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.list.ListFragment
import com.softsquared.template.kotlin.src.main.profile.ProfileFragment
import com.softsquared.template.kotlin.src.main.profile.ProfileFragmentInterface
import com.softsquared.template.kotlin.src.main.profile.ProfileService
import com.softsquared.template.kotlin.src.main.profile.models.ProfileResponse
import com.softsquared.template.kotlin.src.main.search.Search1Fragment
import com.softsquared.template.kotlin.src.main.user.UserFragment
import com.softsquared.template.kotlin.src.start.join.*
import com.softsquared.template.kotlin.src.start.login.LoginFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), ProfileFragmentInterface {

    lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainBtmNav.itemIconTintList = null

        val userId = ApplicationClass.sSharedPreferences.getInt("userId", -1)
        ProfileService(this).tryGetProfile(userId)

        binding.mainBtmNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        fragmentController(resources.getString(R.string.home_fragment), f, f)
                    }
                    R.id.menu_main_btm_nav_search -> {
                        fragmentController(resources.getString(R.string.search_fragment), f, f)
                    }
                    R.id.menu_main_btm_nav_plus -> {
                        startActivity(Intent(this@MainActivity, AddActivity::class.java))
                        finish()
                    }
                    R.id.menu_main_btm_nav_reels -> {

                    }
                    R.id.menu_main_btm_nav_profile -> {
                        fragmentController(resources.getString(R.string.profile_fragment), f, f)
                    }
                }
                true
            }
            selectedItemId = R.id.menu_main_btm_nav_home
        }
    }

    fun fragmentController(name: String, add: Boolean, animate: Boolean) {

        when (name) {
            resources.getString(R.string.home_fragment) -> {
                currentFragment = HomeFragment()
            }
            resources.getString(R.string.profile_fragment) -> {
                currentFragment = ProfileFragment()
            }
            resources.getString(R.string.list_fragment) -> {
                currentFragment = ListFragment()
            }
            resources.getString(R.string.user_fragment) -> {
                currentFragment = UserFragment()
            }
            resources.getString(R.string.search_fragment) -> {
                currentFragment = Search1Fragment()
            }
        }

        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.main_frm, currentFragment)

        if (add) {
            trans.addToBackStack(name)
        }

        if (animate) {
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }

        trans.commit()
    }

    fun fragmentRemoveBackStack(name: String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onGetProfileSuccess(response: ProfileResponse) {
        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putString("profileNickName", response.result.nickname)
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