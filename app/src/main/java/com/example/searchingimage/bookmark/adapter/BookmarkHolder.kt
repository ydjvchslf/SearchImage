package com.example.searchingimage.bookmark.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.searchingimage.databinding.LayoutDataItemBinding
import com.example.searchingimage.repository.entity.Photo
import com.example.searchingimage.util.AppDebug

class BookmarkHolder(binding: LayoutDataItemBinding, private val adapter: BookmarkAdapter): RecyclerView.ViewHolder(binding.root) {

    private val logTag = BookmarkHolder::class.simpleName ?: ""

    var imageView = binding.imageView
    var heartMark = binding.heartMark

    init {
        AppDebug.d(logTag, "init-()")
    }
    // 데이터와 뷰를 묶는다.
    fun bind(photo: Photo) {
        AppDebug.i(logTag, "bind-()")
        imageView.apply {
            Glide.with(itemView)
                .load(photo.thumb)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .into(imageView)
        }
        heartMark.visibility = View.VISIBLE
    }
}