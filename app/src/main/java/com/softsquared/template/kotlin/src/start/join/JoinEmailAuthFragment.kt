package com.softsquared.template.kotlin.src.start.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentJoinEmailAuthBinding
import com.softsquared.template.kotlin.src.start.StartActivity

class JoinEmailAuthFragment : BaseFragment<FragmentJoinEmailAuthBinding>(FragmentJoinEmailAuthBinding::bind, R.layout.fragment_join_email_auth) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val em = ApplicationClass.sSharedPreferences.getString("email", "email@email.com")
        binding.joinEmailAuthText.text = resources.getString(R.string.joinEmailAuth_sub_title, em)

        val act = activity as StartActivity

        binding.toolbar.navigationIcon = requireContext().getDrawable(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            act.fragmentRemoveBackStack(resources.getString(R.string.join_email_auth_fragment))
        }
        binding.joinEmailAuthBtnNext.setOnClickListener {
            act.fragmentController(resources.getString(R.string.join_name_fragment), t, t)
        }
    }
}