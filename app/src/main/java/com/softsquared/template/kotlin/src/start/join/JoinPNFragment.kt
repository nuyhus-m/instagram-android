package com.softsquared.template.kotlin.src.start.join

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentJoinPNBinding
import com.softsquared.template.kotlin.src.start.StartActivity

class JoinPNFragment :
    BaseFragment<FragmentJoinPNBinding>(FragmentJoinPNBinding::bind, R.layout.fragment_join_p_n) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act =activity as StartActivity

        binding.toolbar.navigationIcon = requireContext().getDrawable(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            act.fragmentRemoveBackStack(resources.getString(R.string.join_p_n_fragment))
        }
        binding.joinPNBtnNext.setOnClickListener {
            val editor : SharedPreferences.Editor = ApplicationClass.sSharedPreferences.edit()
            editor.putString("phoneNumber", binding.joinPNEt.text.toString())
            editor.apply()

            act.fragmentController(resources.getString(R.string.join_p_n_auth_fragment), t, t)
        }
        binding.joinPNBtnEmail.setOnClickListener {
            act.fragmentController(resources.getString(R.string.join_email_fragment), t, t)
        }
    }
}