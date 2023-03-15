package com.softsquared.template.kotlin.src.main.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentPostBinding
import com.softsquared.template.kotlin.src.main.MainActivity

class PostFragment : BaseFragment<FragmentPostBinding>(FragmentPostBinding::bind, R.layout.fragment_post) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.postToolbar.navigationIcon = requireContext().getDrawable(R.drawable.ic_back_resize)
        binding.postToolbar.setNavigationOnClickListener {
            val act = activity as MainActivity
            act.fragmentRemoveBackStack(resources.getString(R.string.post_fragment))
        }
    }
}