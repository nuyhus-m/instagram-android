package com.softsquared.template.kotlin.src.add.gallery

import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentGalleryBinding
import com.softsquared.template.kotlin.src.add.AddActivity
import com.softsquared.template.kotlin.src.add.gallery.adapters.GalleryAdapter
import org.jetbrains.anko.image


class GalleryFragment : BaseFragment<FragmentGalleryBinding>(FragmentGalleryBinding::bind, R.layout.fragment_gallery), GalleryInterface {

    private val uriArr = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = activity as AddActivity

        binding.galleryToolbar.navigationIcon = requireContext().getDrawable(com.softsquared.template.kotlin.R.drawable.ic_x_resize)
        binding.galleryToolbar.setNavigationOnClickListener {
            act.fragmentRemoveBackStack(resources.getString(R.string.gallery_fragment))
        }
        binding.galleryToolbarBtnGo.setOnClickListener {
            act.fragmentController(resources.getString(R.string.upload_fragment), t, t)
        }

        getAllPhotos()

        Glide.with(requireContext())
            .load(uriArr[0])
            .into(binding.galleryImg)

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

        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putString("uri", uri)
        editor.apply()

//        val getUri = getUriFromPath(uri)
        Log.d("plz","uri:${uri}")
    }

//    fun getUriFromPath(filePath: String): Uri? {
//        val cursor: Cursor? = requireContext().contentResolver.query(
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//            null, "_data = '$filePath'", null, null
//        )
//        cursor?.moveToNext()
//        val id: Int = cursor?.getInt(cursor.getColumnIndex("_id")) ?: 0
//        return ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toLong())
//    }
}