package com.example.bollymovies.database

import androidx.room.*
import com.example.bollymovies.modeldb.MovieDb
import com.example.bollymovies.modeldb.MovieWithGenres

@Dao
interface MovieDao {

    @Transaction
    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): List<MovieWithGenres>

    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    suspend fun loadMovieById(movieId: Int): List<MovieDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(moviesList: List<MovieDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieDb)

    @Delete
    suspend fun delete(movie: MovieDb)
}