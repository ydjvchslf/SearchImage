package com.example.searchingimage.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.searchingimage.R
import com.example.searchingimage.databinding.FragmentDetailBinding
import com.example.searchingimage.repository.entity.Photo.Companion.fromUnsplash
import com.example.searchingimage.util.AppDebug
import com.example.searchingimage.util.fragTitle

class DetailFragment: Fragment() {

    private val logTag = DetailFragment::class.simpleName
    private lateinit var binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by activityViewModels()
    // 클릭 리스너로 넘어온 Photo
    private val arg: DetailFragmentArgs by navArgs()
    // 좋아요 상태
    private var isUserLike = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        with(binding) {
            viewModel = detailViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        AppDebug.i(logTag, "onCreateView-()")
        fragTitle.value = "Detail"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppDebug.i(logTag, "onViewCreated-()")
        context?.let { detailViewModel.createDb(it) }
        arg.photo?.let { photo ->
            binding.myId.text = photo.id
            binding.myAuthor.text = photo.user.username
            binding.mySize.text = "${photo.width} x ${photo.height}"
            binding.myCreated.text = photo.created_at
            Glide.with(binding.imageView)
                .load(photo.urls.regular)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .into(binding.imageView)
            // 좋아요 판별
            detailViewModel.checkCurrentData(photo.id)
            // 좋아요 해제 버튼
            binding.insertBtn.setOnClickListener {
                detailViewModel.insertPhoto(fromUnsplash(photo))
            }
            binding.deleteBtn.setOnClickListener {
                detailViewModel.deletePhoto(fromUnsplash(photo))
            }
        }
    }
}