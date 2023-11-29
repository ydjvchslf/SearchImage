package com.example.searchingimage.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.searchingimage.R
import com.example.searchingimage.databinding.FragmentSearchBinding
import com.example.searchingimage.util.AppDebug
import com.example.searchingimage.util.fragTitle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment() {

    private val logTag = SearchFragment::class.simpleName
    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by activityViewModels()

    val textChange: PublishSubject<String> = PublishSubject.create()

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
        fragTitle.value = "Search"
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
        // editText 포커싱, 검색어 rxJava
        binding.editText.apply {
            //requestFocus()
            addTextChangedListener(object: TextWatcher{
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    textChange.onNext(s.toString())
                }
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            })

            textChange
                .debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { keyword ->
                    if (keyword.isNotEmpty() && keyword.isNotBlank()) {
                        AppDebug.d(logTag, "keyword => $keyword")
                    } else {
                        AppDebug.d(logTag, "아무것도 안적었슈")
                    }
                }
                .subscribe()
        }
    }

}