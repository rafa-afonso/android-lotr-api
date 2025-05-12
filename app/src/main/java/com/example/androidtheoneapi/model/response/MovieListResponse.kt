package com.example.androidtheoneapi.model.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("docs")
    val movies: List<MovieResponse>,
    val limit: Int,
    val offset: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)