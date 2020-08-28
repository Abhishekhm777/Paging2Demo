package com.example.paging2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class PagingViewModel(private val repository: Repository) :ViewModel() {
    private var currentSearchResult: Flow<PagingData<Item>>? = null
    private  val TAG = "PagingViewModel"
    fun searchRepo(queryString: Int): Flow<PagingData<Item>> {
        val lastResult = currentSearchResult


        val newResult: Flow<PagingData<Item>> = repository.getResults(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        Log.e(TAG, "searchRepo: "+newResult)
        return newResult
    }
}