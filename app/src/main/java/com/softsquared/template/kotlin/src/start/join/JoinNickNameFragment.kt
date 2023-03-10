package com.softsquared.template.kotlin.src.start.join

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentJoinNickNameBinding
import com.softsquared.template.kotlin.src.start.StartActivity
import com.softsquared.template.kotlin.src.start.join.models.JoinEmailRequest
import com.softsquared.template.kotlin.src.start.join.models.JoinPNRequest
import com.softsquared.template.kotlin.src.start.join.models.JoinResponse

class JoinNickNameFragment : BaseFragment<FragmentJoinNickNameBinding>(FragmentJoinNickNameBinding::bind, R.layout.fragment_join_nick_name), JoinInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.navigationIcon = requireContext().getDrawable(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            val act = activity as StartActivity
            act.fragmentRemoveBackStack(resources.getString(R.string.join_nick_name_fragment))
        }
        binding.joinNickNameBtnNext.setOnClickListener {
            val editor : SharedPreferences.Editor = ApplicationClass.sSharedPreferences.edit()
            editor.putString("nickName", binding.joinNickNameEt.text.toString())
            editor.apply()

            val type = ApplicationClass.sSharedPreferences.getInt("type", 0)
            val key: String = if(type == 1) {
                ApplicationClass.sSharedPreferences.getString("phoneNumber", "01012345678")!!
            } else {
                ApplicationClass.sSharedPreferences.getString("email", "email@email.com")!!
            }
            val bd = ApplicationClass.sSharedPreferences.getString("birthDay","0000-00-00")!!
            val nn = ApplicationClass.sSharedPreferences.getString("nickName", "nickname")!!
            val pw = ApplicationClass.sSharedPreferences.getString("password", "pw")!!
            if (type == 1) {
                val joinPNRequest = JoinPNRequest(key, bd, nn, pw)
                JoinService(this).tryPostJoinPN(joinPNRequest)
            } else {
                val joinEmailRequest = JoinEmailRequest(key, bd, nn, pw)
                JoinService(this).tryPostJoinEmail(joinEmailRequest)
            }
        }
    }

    override fun onPostJoinSuccess(response: JoinResponse) {
        if(response.code == 1000){
            val act = activity as StartActivity
            act.fragmentController(resources.getString(R.string.join_photo_fragment), t, t)
        }
    }

    override fun onPostJoinFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}