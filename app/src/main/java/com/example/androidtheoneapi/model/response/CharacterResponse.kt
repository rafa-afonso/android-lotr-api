package com.example.androidtheoneapi.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterResponse(
    @SerializedName("_id")
    val id: String,
    val birth: String?,
    val death: String?,
    val gender: String?,
    val hair: String?,
    val height: String?,
    val name: String?,
    val race: String?,
    val realm: String?,
    val spouse: String?,
    val wikiUrl: String?
) : Serializable