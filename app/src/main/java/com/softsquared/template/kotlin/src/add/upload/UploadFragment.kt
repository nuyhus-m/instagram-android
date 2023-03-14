package com.softsquared.template.kotlin.src.add.upload

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentUploadBinding
import com.softsquared.template.kotlin.src.add.AddActivity

class UploadFragment : BaseFragment<FragmentUploadBinding>(FragmentUploadBinding::bind, R.layout.fragment_upload) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = activity as AddActivity

        binding.uploadToolbar.navigationIcon = requireContext().getDrawable(R.drawable.ic_back_resize)
        binding.uploadToolbar.setNavigationOnClickListener {
            act.fragmentRemoveBackStack(resources.getString(R.string.upload_fragment))
        }
    }
}