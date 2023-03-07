package com.softsquared.template.kotlin.src.start.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentJoinEmailBinding
import com.softsquared.template.kotlin.src.start.StartActivity

class JoinEmailFragment : BaseFragment<FragmentJoinEmailBinding>(FragmentJoinEmailBinding::bind,R.layout.fragment_join_email) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act =activity as StartActivity

        binding.toolbar.navigationIcon = requireContext().getDrawable(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            act.fragmentRemoveBackStack(resources.getString(R.string.join_email_fragment))
        }
        binding.joinEmailBtnPN.setOnClickListener {
            act.fragmentController(resources.getString(R.string.join_p_n_fragment), f, t)
        }
    }

}