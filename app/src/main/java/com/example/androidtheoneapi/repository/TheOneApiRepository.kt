package com.example.androidtheoneapi.repository

import com.example.androidtheoneapi.model.response.BookListResponse
import com.example.androidtheoneapi.model.response.CharacterListResponse
import com.example.androidtheoneapi.model.response.MovieListResponse
import com.example.androidtheoneapi.model.response.QuoteListResponse
import com.example.androidtheoneapi.util.Resource

interface TheOneApiRepository {

    suspend fun getBooks(): Resource<BookListResponse>

    suspend fun getMovies(): Resource<MovieListResponse>

    suspend fun getQuotesPaginated(page: Int, limit: Int?): Resource<QuoteListResponse>

    suspend fun getCharactersPaginated(
        page: Int,
        limit: Int?,
        name: String?
    ): Resource<CharacterListResponse>
}