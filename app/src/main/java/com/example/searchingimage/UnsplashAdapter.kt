package com.example.searchingimage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.searchingimage.data.response.UnsplashPhoto
import com.example.searchingimage.databinding.LayoutDataItemBinding
import com.example.searchingimage.util.AppDebug
import com.example.searchingimage.util.currentBookmarkList

class UnsplashAdapter(private val listener: OnItemClickListener): PagingDataAdapter<UnsplashPhoto, UnsplashAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

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

    inner class PhotoViewHolder(private val binding: LayoutDataItemBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(photo: UnsplashPhoto) {
            binding.apply {
                Glide.with(itemView)
                    .load(photo.urls.thumb)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(com.google.android.material.R.drawable.mtrl_ic_error)
                    .into(imageView)
            }
            // 동기화
            if (synchronizeBookmark(photo)) binding.heartMark.visibility = View.VISIBLE else binding.heartMark.visibility = View.GONE
        }

        fun synchronizeBookmark(photo: UnsplashPhoto): Boolean {
            var result = false
            currentBookmarkList?.observeForever { bookmarkList ->
                result = bookmarkList.any { it.id == photo.id }
            }
            return result
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: UnsplashPhoto)
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