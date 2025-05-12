package com.example.androidtheoneapi.model.response

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("_id")
    val id: String,
    val name: String
)