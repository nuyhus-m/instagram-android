package com.softsquared.template.kotlin.src.start.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentJoinNameBinding
import com.softsquared.template.kotlin.src.start.StartActivity

class JoinNameFragment : BaseFragment<FragmentJoinNameBinding>(FragmentJoinNameBinding::bind, R.layout.fragment_join_name) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = activity as StartActivity

        binding.toolbar.navigationIcon = requireContext().getDrawable(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            act.fragmentRemoveBackStack(resources.getString(R.string.join_name_fragment))
        }
    }
}