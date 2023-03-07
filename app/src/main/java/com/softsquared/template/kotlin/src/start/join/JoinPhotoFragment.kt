package com.softsquared.template.kotlin.src.start.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentJoinPhotoBinding
import com.softsquared.template.kotlin.src.start.StartActivity

class JoinPhotoFragment : BaseFragment<FragmentJoinPhotoBinding>(FragmentJoinPhotoBinding::bind, R.layout.fragment_join_photo) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = activity as StartActivity

        binding.joinPhotoBtnSkip.setOnClickListener {
            act.fragmentController(resources.getString(R.string.join_welcome_fragment), f, t)
        }
    }
}