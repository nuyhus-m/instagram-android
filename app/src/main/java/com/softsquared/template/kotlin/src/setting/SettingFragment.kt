package com.softsquared.template.kotlin.src.setting

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.DialogLogoutBinding
import com.softsquared.template.kotlin.databinding.DialogProfileBinding
import com.softsquared.template.kotlin.databinding.FragmentSettingBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.start.StartActivity

class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::bind, R.layout.fragment_setting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingToolbar.navigationIcon = requireContext().getDrawable(com.softsquared.template.kotlin.R.drawable.ic_back_resize)
        binding.settingToolbar.setNavigationOnClickListener {
            val act = activity as MainActivity
            act.fragmentRemoveBackStack(resources.getString(R.string.setting_fragment))
        }
        binding.settingLogout.setOnClickListener {
            val dialog = Dialog(requireContext())
            val logoutDialogBinding : DialogLogoutBinding = DialogLogoutBinding.inflate(layoutInflater)
            dialog.setContentView(logoutDialogBinding.root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            logoutDialogBinding.dialogBtnLogoutLayout.setOnClickListener {
                dialog.dismiss()
                startActivity(Intent(requireContext(), StartActivity()::class.java))
                requireActivity().finish()
            }
            logoutDialogBinding.dialogBtnNoLayout.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}