package com.example.bollymovies.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watched_movies")
class WatchedMoviesList(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val movieId: Int?,
    var title: String?,
    @ColumnInfo(name = "poster_path")
    var posterPath: String?,
)
