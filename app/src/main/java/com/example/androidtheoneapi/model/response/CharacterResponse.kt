package com.example.androidtheoneapi.model.response

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("_id")
    val id: String,
    val birth: String,
    val death: String,
    val gender: String,
    val hair: String,
    val height: Any,
    val name: String,
    val race: String,
    val realm: String,
    val spouse: String,
    val wikiUrl: String
)