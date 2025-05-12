package com.example.androidtheoneapi.api


import com.example.androidtheoneapi.model.response.BookListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheOneApiAPI {

    @GET("book")
    suspend fun getBooks(
        @Query("page")
        page: Int = 1
    ): Response<BookListResponse>

}