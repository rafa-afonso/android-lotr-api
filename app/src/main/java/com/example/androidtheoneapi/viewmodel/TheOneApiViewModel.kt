package com.example.androidtheoneapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtheoneapi.model.response.BookListResponse
import com.example.androidtheoneapi.model.response.CharacterListResponse
import com.example.androidtheoneapi.model.response.MovieListResponse
import com.example.androidtheoneapi.model.response.QuoteListResponse
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

    private val _quotes = MutableLiveData<Resource<QuoteListResponse>>()
    val quotes: LiveData<Resource<QuoteListResponse>> = _quotes
    private var quotesList: QuoteListResponse? = null
    var quotesPage = 1

    private val _characters = MutableLiveData<Resource<CharacterListResponse>>()
    val characters: LiveData<Resource<CharacterListResponse>> = _characters
    private var charactersList: CharacterListResponse? = null
    var charactersPage = 1
    var newSearchQuery: String? = null
    var oldSearchQuery: String? = null

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

    fun getQuotes(limit: Int?) {
        _quotes.postValue(Resource.Loading())
        viewModelScope.launch {
            val response = repository.getQuotesPaginated(quotesPage, limit)
            _quotes.postValue(handleQuotesResponse(response))
        }
    }

    fun getSearchCharacters(limit: Int? = null, name: String) {
        val regexTreatedName = "/$name/i"
        newSearchQuery = regexTreatedName
        getCharacters(limit, regexTreatedName)
    }

    fun getCharacters(limit: Int? = null, name: String = "") {
        _characters.postValue(Resource.Loading())
        viewModelScope.launch {
            val response = repository.getCharactersPaginated(charactersPage, limit, name)
            val handledCharacterResponse = if (name.isBlank()) {
                handleCharactersResponse(response)
            } else {
                handleSearchCharacterResponse(response)
            }
            _characters.postValue(handledCharacterResponse!!)
        }
    }

    private fun handleQuotesResponse(response: Resource<QuoteListResponse>): Resource<QuoteListResponse> {
        if (response is Resource.Success<QuoteListResponse>) {
            response.data?.let { resultResponse ->
                quotesPage++
                if (quotesList == null) {
                    quotesList = resultResponse
                } else {
                    val oldQuotes = quotesList?.quotes
                    val newQuotes = resultResponse.quotes
                    oldQuotes?.addAll(newQuotes)
                }
                return Resource.Success(quotesList ?: resultResponse)
            }
        }
        return Resource.Error(response.message ?: "")
    }

    private fun handleCharactersResponse(response: Resource<CharacterListResponse>): Resource<CharacterListResponse>? {
        if (response is Resource.Success<CharacterListResponse>) {
            response.data?.let { resultResponse ->
                charactersPage++
                if (charactersList == null) {
                    charactersList = resultResponse
                } else {
                    val oldCharacters = charactersList?.characters
                    val newCharacters = resultResponse.characters
                    oldCharacters?.addAll(newCharacters)
                }
                return Resource.Success(charactersList ?: resultResponse)
            }
        }
        return Resource.Error(response.message ?: "")
    }

    private fun handleSearchCharacterResponse(response: Resource<CharacterListResponse>): Resource<CharacterListResponse> {
        if (response is Resource.Success<CharacterListResponse>) {
            response.data?.let { searchResponse ->
                if (charactersList == null || newSearchQuery != oldSearchQuery) {
                    charactersPage = 1
                    oldSearchQuery = newSearchQuery
                    charactersList = searchResponse
                } else {
                    charactersPage++
                    val oldCharacters = charactersList?.characters
                    val newCharacters = searchResponse.characters
                    oldCharacters?.addAll(newCharacters)
                }
                return Resource.Success(charactersList ?: searchResponse)
            }
        }
        return Resource.Error(response.message ?: "")
    }

}