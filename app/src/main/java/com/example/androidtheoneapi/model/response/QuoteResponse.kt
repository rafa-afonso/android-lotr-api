package com.example.androidtheoneapi.model.response

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("_id")
    val id: String,
    val character: String,
    val dialog: String,
    val movie: String
)