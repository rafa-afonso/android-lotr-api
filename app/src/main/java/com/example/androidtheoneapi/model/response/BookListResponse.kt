package com.example.androidtheoneapi.model.response

import com.google.gson.annotations.SerializedName

data class BookListResponse(
    @SerializedName("docs")
    val books: List<BookResponse>,
    val limit: Int,
    val offset: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)