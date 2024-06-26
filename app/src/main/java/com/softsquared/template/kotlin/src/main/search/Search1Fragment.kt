package com.softsquared.template.kotlin.src.main.search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentSearch1Binding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.profile.adpaters.ProfilePostAdapter
import com.softsquared.template.kotlin.src.main.search.models.SearchResponse

class Search1Fragment : BaseFragment<FragmentSearch1Binding>(FragmentSearch1Binding::bind, R.layout.fragment_search1), SearchFragmentInterface {

    private val postList = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SearchService(this).tryGetAllPosts()

        binding.search1Et.setOnFocusChangeListener{ view: View, b: Boolean ->
            if(b) {
                val act = activity as MainActivity
                act.fragmentController(resources.getString(R.string.search2_fragment), t, f)
            }
        }
    }

    override fun onGetAllPostsSuccess(response: SearchResponse) {
        postList.clear()
        for (i in 0 until response.result.size) {
            postList.add(response.result[i].firstPhotoUrl)
        }
        val act = activity as MainActivity
        binding.searchRv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.searchRv.adapter = ProfilePostAdapter(postList, act)
    }

    override fun onGetAllPostsFailure(message: String) {
        showCustomToast("오류: $message")
    }
}