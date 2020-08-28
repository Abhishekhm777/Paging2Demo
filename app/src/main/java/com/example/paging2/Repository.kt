package com.example.paging2

import android.content.ContentValues.TAG
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class Repository(private val service: ResultsService) {
    fun getResults(userId: Int): Flow<PagingData<Item>> {
        val pager= Pager(
            config = PagingConfig(pageSize = 15, enablePlaceholders = true),
            pagingSourceFactory = {
                ResultPagingsource(
                    service,
                    userId
                )
            }
        ).flow
        Log.e(TAG, "getResults: "+pager )
return  pager
    }
}