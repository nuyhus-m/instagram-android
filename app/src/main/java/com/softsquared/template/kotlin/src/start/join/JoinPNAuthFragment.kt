package com.softsquared.template.kotlin.src.start.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentJoinPNAuthBinding
import com.softsquared.template.kotlin.src.start.StartActivity

class JoinPNAuthFragment : BaseFragment<FragmentJoinPNAuthBinding>(FragmentJoinPNAuthBinding::bind,R.layout.fragment_join_p_n_auth) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pn = ApplicationClass.sSharedPreferences.getString("phoneNumber", "01000000000")
        binding.joinPNAuthText.text = resources.getString(R.string.joinPNAuth_sub_title, pn)

        val act =activity as StartActivity

        binding.toolbar.navigationIcon = requireContext().getDrawable(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            act.fragmentRemoveBackStack(resources.getString(R.string.join_p_n_auth_fragment))
        }
        binding.joinPNAuthBtnNext.setOnClickListener {
            act.fragmentController(resources.getString(R.string.join_name_fragment), t, t)
        }
    }
}