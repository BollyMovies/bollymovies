package com.example.bollymovies.database

import androidx.room.*

@Dao
interface WatchedMoviesListDao {

    @Query("SELECT * FROM watched_movies")
    suspend fun getAllFavorites(): List<WatchedMoviesList>

    @Query("SELECT * FROM list_movies WHERE id = :Id")
    suspend fun loadFavoritesById(Id: Int): List<WatchedMoviesList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavorites(WatchedMoviesList: List<WatchedMoviesList>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(movie: WatchedMoviesList)

    @Delete
    suspend fun delete(movie: WatchedMoviesList)
}