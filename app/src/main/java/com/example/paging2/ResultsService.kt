package com.example.paging2

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ResultsService {

    @GET("listitem/{id}")
    suspend fun getResult(
        @Header("Authorization")auth:String,
        @Path("id") id: Int,
        @Query("page") page: Int,
        @Query("size") size: Int
    ):DataModel


}