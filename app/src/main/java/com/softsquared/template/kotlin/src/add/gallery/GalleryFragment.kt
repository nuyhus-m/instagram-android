package com.softsquared.template.kotlin.src.add.gallery

import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentGalleryBinding
import com.softsquared.template.kotlin.src.add.gallery.adapters.GalleryAdapter

class GalleryFragment : BaseFragment<FragmentGalleryBinding>(FragmentGalleryBinding::bind, R.layout.fragment_gallery), GalleryInterface {

    private val uriArr = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.galleryToolbar.navigationIcon = requireContext().getDrawable(com.softsquared.template.kotlin.R.drawable.ic_x_resize)
        binding.galleryToolbar.setNavigationOnClickListener {

        }

        getAllPhotos()
        binding.galleryRv.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.galleryRv.adapter = GalleryAdapter(uriArr, this)

    }

    private fun getAllPhotos(){
        val cursor = requireContext().contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC")
        if(cursor!=null){
            while(cursor.moveToNext()){
                // 사진 경로 Uri 가져오기
                val uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                uriArr.add(uri)
            }
            cursor.close()
        }
    }

    override fun changeGallery(uri: String) {
        Glide.with(requireContext())
            .load(uri)
            .into(binding.galleryImg)
    }
}