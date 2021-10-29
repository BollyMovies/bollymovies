package com.example.bollymovies.model

data class NowPlaying(
    val dates: Dates,
    val page: Int,
    var results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)