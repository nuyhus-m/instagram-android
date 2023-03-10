package com.softsquared.template.kotlin.src.start.join

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentJoinPasswordBinding
import com.softsquared.template.kotlin.src.start.StartActivity

class JoinPasswordFragment : BaseFragment<FragmentJoinPasswordBinding>(FragmentJoinPasswordBinding::bind, R.layout.fragment_join_password) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = activity as StartActivity

        binding.toolbar.navigationIcon = requireContext().getDrawable(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            act.fragmentRemoveBackStack(resources.getString(R.string.join_password_fragment))
        }
        binding.joinPasswordBtnNext.setOnClickListener {
            val editor : SharedPreferences.Editor = ApplicationClass.sSharedPreferences.edit()
            editor.putString("password", binding.joinPwEt.text.toString())
            editor.apply()

            act.fragmentController(resources.getString(R.string.join_birth_day_fragment), t, t)
        }
    }
}