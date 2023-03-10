package com.softsquared.template.kotlin.src.start.join

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentJoinWelcomeBinding
import com.softsquared.template.kotlin.src.main.MainActivity

class JoinWelcomeFragment : BaseFragment<FragmentJoinWelcomeBinding>(FragmentJoinWelcomeBinding::bind, R.layout.fragment_join_welcome) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nn = ApplicationClass.sSharedPreferences.getString("nickName", "nickname")
        binding.joinWelcomeText.text = resources.getString(R.string.joinWelcome_text1, nn)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }, 1500)
    }
}