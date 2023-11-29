package com.example.searchingimage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.searchingimage.data.response.UnsplashPhoto
import com.example.searchingimage.databinding.LayoutDataItemBinding

class UnsplashAdapter: PagingDataAdapter<UnsplashPhoto, UnsplashAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = LayoutDataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    class PhotoViewHolder(private val binding: LayoutDataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(photo: UnsplashPhoto) {
                binding.apply {
                    Glide.with(itemView)
                        .load(photo.urls.regular)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(com.google.android.material.R.drawable.mtrl_ic_error)
                        .into(imageView)
                }
            }
        }

    companion object {
        private val PHOTO_COMPARATOR = object: DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UnsplashPhoto,
                newItem: UnsplashPhoto
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}