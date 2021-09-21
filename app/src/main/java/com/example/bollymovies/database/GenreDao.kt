package com.example.bollymovies.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.bollymovies.modeldb.GenreDb

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    suspend fun getAllGenres(): List<GenreDb>

    @Query("SELECT * FROM genre WHERE genreId = :genreId")
    suspend fun loadGenreById(genreId: Int): List<GenreDb>

    @Insert(onConflict = REPLACE)
    suspend fun insertAllGenres(moviesList: List<GenreDb>)

    @Insert(onConflict = REPLACE)
    suspend fun insertGenre(genre: GenreDb)

    @Delete
    suspend fun delete(genreDb: GenreDb)
}