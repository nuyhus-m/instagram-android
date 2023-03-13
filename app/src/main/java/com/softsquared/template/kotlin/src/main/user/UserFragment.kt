package com.softsquared.template.kotlin.src.main.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentUserBinding
import com.softsquared.template.kotlin.src.main.MainActivity

class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::bind, R.layout.fragment_user) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userToolbar.navigationIcon = requireContext().getDrawable(com.softsquared.template.kotlin.R.drawable.ic_back_resize)
        binding.userToolbar.setNavigationOnClickListener {
            val act = activity as MainActivity
            act.fragmentRemoveBackStack(resources.getString(R.string.user_fragment))
        }
    }
}