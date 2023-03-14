package com.softsquared.template.kotlin.src.add.gallery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.databinding.FragmentGalleryBinding
import com.softsquared.template.kotlin.databinding.ItemGalleryBinding
import com.softsquared.template.kotlin.src.add.gallery.GalleryInterface

class GalleryAdapter(private val uriArr: ArrayList<String>, private val galleryInterface: GalleryInterface) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){

    inner class GalleryViewHolder(private val galleryItemBinding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(galleryItemBinding.root) {
        fun bind(uri:String) {
            Glide.with(itemView)
                .load(uri)
                .into(galleryItemBinding.galleyItemImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding).also { holder ->
            binding.galleyItemImg.setOnClickListener {
                galleryInterface.changeGallery(uriArr[holder.adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(uriArr[position])
    }

    override fun getItemCount(): Int {
        return uriArr.size
    }
}