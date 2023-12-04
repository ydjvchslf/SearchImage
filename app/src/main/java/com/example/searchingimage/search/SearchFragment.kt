package com.example.searchingimage.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.GridLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.paging.PagingData
import androidx.paging.log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.searchingimage.R
import com.example.searchingimage.UnsplashAdapter
import com.example.searchingimage.data.response.UnsplashPhoto
import com.example.searchingimage.databinding.FragmentSearchBinding
import com.example.searchingimage.repository.db.AppDatabase
import com.example.searchingimage.repository.entity.Photo
import com.example.searchingimage.util.AppDebug
import com.example.searchingimage.util.currentBookmarkList
import com.example.searchingimage.util.fragTitle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment(), UnsplashAdapter.OnItemClickListener {

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
        // val db = Room.databaseBuilder(context!!, AppDatabase::class.java, "photoDB").allowMainThreadQueries().build() // 에러 없음
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppDebug.i(logTag, "onViewCreated-()")
        context?.let { searchViewModel.createDb(it) }
        binding.detailBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment())
        }
        binding.bookmarkBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(SearchFragmentDirections.actionSearchFragmentToBookmarkFragment())
        }
        //binding.getBtn.setOnClickListener {
            searchViewModel.photoList?.observe(viewLifecycleOwner) { photoList ->
                AppDebug.d(logTag, "photoListSize : ${photoList.size}")
                AppDebug.d(logTag, "photoList : $photoList")
            }
        //}
        // 리사이클러뷰
        val adapter = UnsplashAdapter(this)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 4)
            setHasFixedSize(true)
            this.adapter = adapter
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
                        //searchViewModel.searchPhoto(keyword)
                        hideKeyboard()
                        binding.recyclerView.scrollToPosition(0)
                        searchViewModel.currentQuery.value = keyword
                        searchViewModel.photos.observe(viewLifecycleOwner) {
                            adapter.submitData(viewLifecycleOwner.lifecycle, it)
                        }
                    } else {
                        AppDebug.d(logTag, "아무것도 안적었슈")
                        adapter.submitData(viewLifecycleOwner.lifecycle, PagingData.empty())
                    }
                }
                .subscribe()
        }
    }

    override fun onItemClick(photo: UnsplashPhoto) {
        AppDebug.d(logTag, "onItemClick-()")
        AppDebug.d(logTag, "photoId: ${photo.id}")
        val navAction = SearchFragmentDirections.actionSearchFragmentToDetailFragment(photo)
        Navigation.findNavController(binding.root).navigate(navAction)
    }

    private fun hideKeyboard() {
        val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val focusedView = activity?.currentFocus
        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)
            binding.editText.clearFocus() // 포커스 제거
        }
    }

}