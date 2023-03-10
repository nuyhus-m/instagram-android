package com.softsquared.template.kotlin.src.start.join

import android.app.AlertDialog
import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.DialogBirthdayBinding
import com.softsquared.template.kotlin.databinding.FragmentJoinBirthdayBinding
import com.softsquared.template.kotlin.src.start.StartActivity

class JoinBirthdayFragment : BaseFragment<FragmentJoinBirthdayBinding>(FragmentJoinBirthdayBinding::bind, R.layout.fragment_join_birthday) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = activity as StartActivity

        binding.toolbar.navigationIcon = requireContext().getDrawable(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            act.fragmentRemoveBackStack(resources.getString(R.string.join_birth_day_fragment))
        }

        var year = 2023
        var month = 3
        var day = 9

        binding.joinBirthdayValue.text = "${year}년 ${month}월 ${day}일"

        binding.joinBirthdayEt.setOnClickListener {
            val birthDayDialog = Dialog(requireContext())
            val birthDayDialogBinding : DialogBirthdayBinding = DialogBirthdayBinding.inflate(layoutInflater)
            birthDayDialog.setContentView(birthDayDialogBinding.root)

            birthDayDialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            birthDayDialog.show()

            birthDayDialogBinding.dialogBirthdayYear.minValue = 1900
            birthDayDialogBinding.dialogBirthdayYear.maxValue = 2100
            birthDayDialogBinding.dialogBirthdayYear.value = 2023
            birthDayDialogBinding.dialogBirthdayYear.setOnValueChangedListener { numberPicker, i, i2 ->
                year = i2
            }

            birthDayDialogBinding.dialogBirthdayMonth.minValue = 1
            birthDayDialogBinding.dialogBirthdayMonth.maxValue = 12
            birthDayDialogBinding.dialogBirthdayMonth.value = 3
            birthDayDialogBinding.dialogBirthdayMonth.setOnValueChangedListener { numberPicker, i, i2 ->
                month = i2
            }

            birthDayDialogBinding.dialogBirthdayDay.minValue = 1
            birthDayDialogBinding.dialogBirthdayDay.maxValue = 31
            birthDayDialogBinding.dialogBirthdayDay.value = 9
            birthDayDialogBinding.dialogBirthdayDay.setOnValueChangedListener { numberPicker, i, i2 ->
                day = i2
            }

            birthDayDialogBinding.dialogBirthdayNo.setOnClickListener {
                birthDayDialog.dismiss()
            }
            birthDayDialogBinding.dialogBirthdayYes.setOnClickListener {
                binding.joinBirthdayValue.text = resources.getString(R.string.joinBirthDay_et, year, month, day)
                birthDayDialog.dismiss()
            }
        }

        binding.joinBirthBtnNext.setOnClickListener {
            var monthString = "$month"
            if(month < 10) {
                monthString = "0${month}"
            }

            var dayString = "$day"
            if(day < 10) {
                dayString = "0${day}"
            }

            val editor : SharedPreferences.Editor = ApplicationClass.sSharedPreferences.edit()
            editor.putString("birthDay", "${year}-${monthString}-${dayString}")
            editor.apply()

            act.fragmentController(resources.getString(R.string.join_nick_name_fragment), t, t)
        }
    }
}