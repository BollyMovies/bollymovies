package com.example.bollymovies.database

import androidx.room.*
import com.example.bollymovies.model.Result


@Dao
interface MoviesHomeDao {
    @Query("SELECT * FROM movies_home WHERE movieId = :movieId")
    suspend fun getMovieById(movieId: Int): Result

    @Query("SELECT * FROM movies_home WHERE type == 2")
    suspend fun getNowPlaying(): List<Result>

    @Query("SELECT * FROM movies_home WHERE type == 1")
    suspend fun getPopular(): List<Result>

    @Query("SELECT * FROM movies_home WHERE type == 3")
    suspend fun getTopRated(): List<Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(moviesList: List<Result>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Result)

    @Delete
    suspend fun delete(movie: Result)


}