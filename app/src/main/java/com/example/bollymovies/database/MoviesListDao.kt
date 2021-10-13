package com.example.bollymovies.database

import androidx.room.*

@Dao
interface MoviesListDao {

    @Query("SELECT * FROM list_movies")
    suspend fun getAllFavorites(): List<MoviesList>

    @Query("SELECT * FROM list_movies WHERE id = :Id")
    suspend fun loadFavoritesById(Id: Int): List<MoviesList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavorites(moviesList: List<MoviesList>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(movie: MoviesList)

    @Delete
    suspend fun delete(movie: MoviesList)
}