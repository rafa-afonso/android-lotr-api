package com.example.androidtheoneapi.api


import com.example.androidtheoneapi.model.response.BookListResponse
import com.example.androidtheoneapi.model.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheOneApiAPI {

    @GET("book")
    suspend fun getBooks(
        @Query("page")
        page: Int = 1
    ): Response<BookListResponse>

    @GET("movie")
    suspend fun getMovies(
        @Query("page")
        page: Int = 1,
        @Query("name!")
        exclude: String = "/series/i" // remove series results from api call
    ): Response<MovieListResponse>

}