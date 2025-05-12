package com.example.androidtheoneapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtheoneapi.model.response.BookListResponse
import com.example.androidtheoneapi.model.response.MovieListResponse
import com.example.androidtheoneapi.repository.TheOneApiRepository
import com.example.androidtheoneapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheOneApiViewModel @Inject constructor(
    private val repository: TheOneApiRepository
) : ViewModel() {

    private val _books = MutableLiveData<Resource<BookListResponse>>()
    val books: LiveData<Resource<BookListResponse>> = _books

    private val _movies = MutableLiveData<Resource<MovieListResponse>>()
    val movies: LiveData<Resource<MovieListResponse>> = _movies

    fun getBooks() {
        _books.postValue(Resource.Loading())
        viewModelScope.launch {
            val response = repository.getBooks()
            _books.postValue(response)
        }
    }

    fun getMovies() {
        _movies.postValue(Resource.Loading())
        viewModelScope.launch {
            val response = repository.getMovies()
            _movies.postValue(response)
        }
    }

}