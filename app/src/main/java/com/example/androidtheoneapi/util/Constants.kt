package com.example.androidtheoneapi.util

import com.example.androidtheoneapi.BuildConfig


class Constants {
    companion object {
        const val API_KEY = BuildConfig.THE_ONE_API_KEY
        const val BASE_URL = "https://the-one-api.dev/v2/"
        const val DEFAULT_PAGINATION_RESULTS_PER_PAGE = 20
        const val SEARCH_TIME_DELAY = 1000L
    }
}