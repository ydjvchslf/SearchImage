package com.example.searchingimage.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.searchingimage.R
import com.example.searchingimage.databinding.FragmentBookmarkBinding
import com.example.searchingimage.search.SearchFragmentDirections
import com.example.searchingimage.util.AppDebug

class BookmarkFragment: Fragment() {

    private val logTag = BookmarkFragment::class.simpleName
    private lateinit var binding: FragmentBookmarkBinding
    private val bookmarkViewModel: BookmarkViewModel by activityViewModels()

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(BookmarkFragmentDirections.actionBookmarkFragmentToDetailFragment())
        }
    }
}