package com.example.bollymovies.modeldb

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithGenres(
    @Embedded val movie: MovieDb,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId"
    )
    val genres: List<GenreDb>
)
