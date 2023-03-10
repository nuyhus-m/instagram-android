package com.softsquared.template.kotlin.src.start.join

import android.content.Intent
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
import com.softsquared.template.kotlin.databinding.FragmentJoinNickNameBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.start.StartActivity
import com.softsquared.template.kotlin.src.start.join.models.JoinPNRequest
import com.softsquared.template.kotlin.src.start.join.models.JoinPNResponse

class JoinNickNameFragment : BaseFragment<FragmentJoinNickNameBinding>(FragmentJoinNickNameBinding::bind, R.layout.fragment_join_nick_name), JoinPNInterface {

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

            val pn:String = ApplicationClass.sSharedPreferences.getString("phoneNumber", "01012345678")!!
            val bd = ApplicationClass.sSharedPreferences.getString("birthDay","0000-00-00")!!
            val nn = ApplicationClass.sSharedPreferences.getString("nickName", "nickname")!!
            val pw = ApplicationClass.sSharedPreferences.getString("password", "pw")!!
            val joinPNRequest = JoinPNRequest(pn,bd,nn,pw)
            Log.d("joinPNData","$joinPNRequest")
            JoinPNService(this).tryPostJoinPN(joinPNRequest)
        }
    }

    override fun onPostJoinPNSuccess(response: JoinPNResponse) {
        if(response.code == 1000){
            val act = activity as StartActivity
            act.fragmentController(resources.getString(R.string.join_photo_fragment), t, t)
        }
    }

    override fun onPostJoinPNFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}