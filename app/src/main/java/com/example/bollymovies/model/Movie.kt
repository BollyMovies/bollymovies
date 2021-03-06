package com.example.bollymovies.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean? = null,
    var backdrop_path: String? = null,
    val budget: Int? = null,
    val credits: MovieCredits? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    var poster_path: String? = null,
    val production_companies: List<ProductionCompany>? = null,
    val production_countries: List<ProductionCountry>? = null,
    val release_date: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val spoken_languages: List<SpokenLanguage>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
    val videos: Videos? = null,
    @SerializedName("watch/providers")
    val watch_providers: Streaming? = null
)