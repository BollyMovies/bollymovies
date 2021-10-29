package com.example.bollymovies.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_movies")
class MoviesList(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val movieId: Int?,
    var title: String?,
    @ColumnInfo(name = "poster_path")
    var posterPath: String?,
)
