package com.example.searchingimage.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.searchingimage.R
import com.example.searchingimage.databinding.FragmentSearchBinding
import com.example.searchingimage.util.AppDebug

class SearchFragment : Fragment() {

    private val logTag = SearchFragment::class.simpleName
    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        with(binding) {
            viewModel = searchViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        AppDebug.i(logTag, "onCreateView-()")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppDebug.i(logTag, "onViewCreated-()")
        binding.detailBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment())
        }
        binding.bookmarkBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(SearchFragmentDirections.actionSearchFragmentToBookmarkFragment())
        }
    }
}