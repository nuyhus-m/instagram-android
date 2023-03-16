package com.softsquared.template.kotlin.src.main.post

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentPostBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.adapters.HomePostAdapter
import com.softsquared.template.kotlin.src.main.profile.ProfilePostFragmentInterface
import com.softsquared.template.kotlin.src.main.profile.ProfilePostService
import com.softsquared.template.kotlin.src.main.profile.models.ProfilePostResponse

class PostFragment : BaseFragment<FragmentPostBinding>(FragmentPostBinding::bind, R.layout.fragment_post),
    ProfilePostFragmentInterface{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.postToolbar.navigationIcon = requireContext().getDrawable(R.drawable.ic_back_resize)
        binding.postToolbar.setNavigationOnClickListener {
            val act = activity as MainActivity
            act.fragmentRemoveBackStack(resources.getString(R.string.post_fragment))
        }

        val userId = ApplicationClass.sSharedPreferences.getInt("userId", -1)
        ProfilePostService(this).tryGetProfilePost(userId)
    }

    override fun onGetProfilePostSuccess(response: ProfilePostResponse) {
        val layoutManager = LinearLayoutManager(requireContext())

        binding.postRv.layoutManager = layoutManager
        binding.postRv.adapter = HomePostAdapter(response.post)
    }

    override fun onGetProfilePostFailure(message: String) {
        showCustomToast("오류: $message")
    }
}