package com.example.androidtheoneapi.model.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("_id")
    val id: String,
    val academyAwardNominations: Int,
    val academyAwardWins: Int,
    val boxOfficeRevenueInMillions: Double,
    val budgetInMillions: Int,
    val name: String,
    val rottenTomatoesScore: Double,
    val runtimeInMinutes: Int
)