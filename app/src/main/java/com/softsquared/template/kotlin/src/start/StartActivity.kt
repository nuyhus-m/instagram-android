package com.softsquared.template.kotlin.src.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityStartBinding
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.start.login.LoginFragment

class StartActivity : BaseActivity<ActivityStartBinding>(ActivityStartBinding::inflate) {

    lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentController(resources.getString(R.string.login_fragment), t, t)
    }

    fun fragmentController(name: String, add: Boolean, animate: Boolean) {

        when (name) {
            resources.getString(R.string.login_fragment) -> {
                currentFragment = LoginFragment()
            }
        }

        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.start_frm, currentFragment)

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
}