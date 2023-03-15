package com.softsquared.template.kotlin.src.main.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentSearch2Binding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.search.adapters.Search2Adapter
import com.softsquared.template.kotlin.src.main.search.models.Search2Response

class Search2Fragment : BaseFragment<FragmentSearch2Binding>(FragmentSearch2Binding::bind, R.layout.fragment_search2), Search2FragmentInterface {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search2Et.requestFocus()
        showKeyboard(binding.search2Et)

        binding.searchBack.setOnClickListener {
            val act = activity as MainActivity
            act.fragmentRemoveBackStack(resources.getString(R.string.search2_fragment))
        }

    }

    private fun showKeyboard(editText: EditText){
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }

    override fun onGetSearchSuccess(response: Search2Response) {
        binding.searchRvSearch.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRvSearch.adapter = Search2Adapter(response.result)
    }

    override fun onGetSearchFailure(message: String) {
        showCustomToast("오류: $message")
        Log.d("오류", message)
    }

}