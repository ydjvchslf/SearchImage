package com.example.searchingimage.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchingimage.R
import com.example.searchingimage.bookmark.adapter.BookmarkAdapter
import com.example.searchingimage.databinding.FragmentBookmarkBinding
import com.example.searchingimage.repository.entity.Photo
import com.example.searchingimage.util.AppDebug
import com.example.searchingimage.util.currentBookmarkList
import com.example.searchingimage.util.fragTitle
import java.util.ArrayList

class BookmarkFragment: Fragment() {

    private val logTag = BookmarkFragment::class.simpleName
    private lateinit var binding: FragmentBookmarkBinding
    private val bookmarkViewModel: BookmarkViewModel by activityViewModels()
    // 사용할 리사이클러뷰 생성
    var bookmarkAdapter = BookmarkAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false)
        with(binding) {
            viewModel = bookmarkViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        AppDebug.i(logTag, "onCreateView-()")
        fragTitle.value = "Bookmark"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(BookmarkFragmentDirections.actionBookmarkFragmentToDetailFragment())
        }

        context?.let { bookmarkViewModel.createDb(it) }

        AppDebug.i(logTag, "===여기 시작???===")
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 4)
            setHasFixedSize(true)
            adapter = bookmarkAdapter
            bookmarkAdapter.submitList(currentBookmarkList?.value as ArrayList<Photo>) // 여기 모르겠다
        }
    }
}