package com.example.paging2

import android.util.Log
import androidx.paging.PagingSource
import com.example.paging2.Item
import com.example.paging2.ResultsService
import retrofit2.HttpException
import java.io.IOException

class ResultPagingsource( private val service: ResultsService,private val userid:Int):
    PagingSource<Int, Item>() {
    private  val TAG = "ResultPagingsource"
     val token ="bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTg2MjUzNzcsInVzZXJfbmFtZSI6IlN1cHBvcnRAbXJrbGxwLmNvbSIsImF1dGhvcml0aWVzIjpbIlJPTEVfV0hPTEVTQUxFUiJdLCJqdGkiOiI1NDA5NDY1Mi1jN2JmLTRiMzgtYmU3Zi1iNjExMzgzODJiMjkiLCJjbGllbnRfaWQiOiJvcmRvZnktd2ViIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.AL-AVRmsJ2KvKF1yBn36qTg9jRpSrPwShMsMu9t5DAk4DwThexjPIgGm9iZjhbZ47i6xSd-LEfhX-gk6QixKa9M7FpmxYnBk-UWdh8lYRZaHt8QpY3v9KrM6bS7_-YNNp09kUNpb5W3BKX1OvS8bSo-Z1b96nKk6ptIJVEtDTRoYEQu17CnBzbc-0u-xGqwO-FXhHSzOexT7Cveoetr_Tn_d47V5x04nejDoaWbW2IDf7fU5FufdCK424jEKJbHiDofJFbYhL6QkLAP7KPtG6X6XX8BjdSOjwyZ1eWaDaa51ms8MA6B3q16gmYRMdJuQGO-Q9VDQQjsg3yc-6OTqmQ"
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val position = params.key ?: 1

        return try {
            val response = service.getResult(token,userid, position, params.loadSize)
            Log.e(TAG, "load: "+response.embedded.itemList )
            val repos = response.embedded.itemList
            LoadResult.Page(
                data = repos,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            Log.e(TAG, "load: "+exception )
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.e(TAG, "load: "+exception )
            LoadResult.Error(exception)
        }
    }
}