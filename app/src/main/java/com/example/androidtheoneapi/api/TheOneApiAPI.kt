package com.example.androidtheoneapi.api


import com.example.androidtheoneapi.model.response.BookListResponse
import com.example.androidtheoneapi.model.response.CharacterListResponse
import com.example.androidtheoneapi.model.response.MovieListResponse
import com.example.androidtheoneapi.model.response.QuoteListResponse
import com.example.androidtheoneapi.util.Constants.Companion.DEFAULT_PAGINATION_RESULTS_PER_PAGE
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

    @GET("quote")
    suspend fun getQuotesPaginated(
        @Query("page")
        page: Int = 1,
        @Query("limit")
        pageLimit: Int = DEFAULT_PAGINATION_RESULTS_PER_PAGE
    ): Response<QuoteListResponse>

    @GET("character")
    suspend fun getCharactersPaginated(
        @Query("page")
        page: Int = 1,
        @Query("limit")
        pageLimit: Int = DEFAULT_PAGINATION_RESULTS_PER_PAGE
    ): Response<CharacterListResponse>

}