package com.example.bollymovies.extensions

import com.example.bollymovies.model.Genre
import com.example.bollymovies.model.Result
import com.example.bollymovies.modeldb.GenreDb
import com.example.bollymovies.modeldb.MovieDb

fun Genre.toGenreDb(): GenreDb {
    return GenreDb(
        id = this.id,
        name = this.name
    )
}

fun Result.toMovieDb(): MovieDb {
    return MovieDb(
        id = this.id,
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}

