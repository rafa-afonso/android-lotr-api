package com.example.androidtheoneapi.repository

import com.example.androidtheoneapi.api.TheOneApiAPI
import com.example.androidtheoneapi.model.response.BookListResponse
import com.example.androidtheoneapi.model.response.MovieListResponse
import com.example.androidtheoneapi.util.Resource
import javax.inject.Inject

class DefaultTheOneApiRepository @Inject constructor(
    private val theOneApiAPI: TheOneApiAPI
) : TheOneApiRepository {
    override suspend fun getBooks(): Resource<BookListResponse> {
        return try {
            val response = theOneApiAPI.getBooks()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("An unknown error has occurred", null)
            } else {
                Resource.Error("An unknown error has occurred", null)
            }
        } catch (e: Exception) {
            Resource.Error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override suspend fun getMovies(): Resource<MovieListResponse> {
        return try {
            val response = theOneApiAPI.getMovies()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("An unknown error has occurred", null)
            } else {
                Resource.Error("An unknown error has occurred", null)
            }
        } catch (e: Exception) {
            Resource.Error("Couldn't reach the server. Check your internet connection", null)
        }
    }


}