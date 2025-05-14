package com.example.androidtheoneapi.model.response

import com.google.gson.annotations.SerializedName

data class CharacterListResponse(
    @SerializedName("docs")
    val characters: MutableList<CharacterResponse>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)