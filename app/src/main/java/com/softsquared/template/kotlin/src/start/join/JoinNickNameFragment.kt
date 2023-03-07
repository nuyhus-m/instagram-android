package com.softsquared.template.kotlin.src.start.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentJoinNickNameBinding
import com.softsquared.template.kotlin.src.start.StartActivity

class JoinNickNameFragment : BaseFragment<FragmentJoinNickNameBinding>(FragmentJoinNickNameBinding::bind, R.layout.fragment_join_nick_name) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = activity as StartActivity

        binding.toolbar.navigationIcon = requireContext().getDrawable(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            act.fragmentRemoveBackStack(resources.getString(R.string.join_nick_name_fragment))
        }
        binding.joinNickNameBtnNext.setOnClickListener {
            act.fragmentController(resources.getString(R.string.join_photo_fragment), t, t)
        }
    }
}