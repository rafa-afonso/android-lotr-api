package com.example.androidtheoneapi.model.response

import com.google.gson.annotations.SerializedName

data class QuoteListResponse(
    @SerializedName("docs")
    val quotes: List<QuoteResponse>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)