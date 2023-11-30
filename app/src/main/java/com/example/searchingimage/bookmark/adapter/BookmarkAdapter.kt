package com.example.searchingimage.bookmark.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchingimage.R
import com.example.searchingimage.databinding.LayoutDataItemBinding
import com.example.searchingimage.repository.entity.Photo
import com.example.searchingimage.util.AppDebug
import java.util.*


class BookmarkAdapter: RecyclerView.Adapter<BookmarkHolder>() {

    private val logTag = BookmarkAdapter::class.simpleName
    var photoList = ArrayList<Photo>()

    var clickListener : ((Photo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkHolder {
        // 연결할 레이아웃 설정
        val item = LayoutInflater.from(parent.context).inflate(R.layout.layout_data_item, parent, false)
        val dataViewHolder = BookmarkHolder(LayoutDataItemBinding.bind(item), this)

        // 리스너 콜백 등록
        item.setOnClickListener {
            AppDebug.i(logTag, "item Clicked-()")
            val position = dataViewHolder.absoluteAdapterPosition
            clickListener?.invoke(photoList[position])
        }
        return dataViewHolder
    }

    override fun onBindViewHolder(holder: BookmarkHolder, position: Int) {
        AppDebug.i(logTag, "onBindViewHolder-()")
        holder.bind(this.photoList[position])
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    // 외부데이터 넘기기 -> adapter에서 갖고 있는 photoList 와 외부에서 들어온 photoList 를 연결해주는 함수
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(photoList: ArrayList<Photo>){
        this.photoList = photoList // 외부데이터를 adapter 데이터로 할당
        notifyDataSetChanged()
    }
}