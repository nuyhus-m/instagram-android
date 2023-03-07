package com.softsquared.template.kotlin.src.start.join

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentJoinPNBinding
import com.softsquared.template.kotlin.src.start.StartActivity

class JoinPNFragment :
    BaseFragment<FragmentJoinPNBinding>(FragmentJoinPNBinding::bind, R.layout.fragment_join_p_n) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.navigationIcon = requireContext().getDrawable(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            val act =activity as StartActivity
            act.fragmentRemoveBackStack("joinPN")
        }
    }
}