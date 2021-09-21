package com.example.bollymovies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bollymovies.modeldb.GenreDb
import com.example.bollymovies.modeldb.MovieDb

object BollyMoviesDatabase {
    @Database(entities = [MovieDb::class, GenreDb::class], version = 1)
    abstract class BollyMoviesRoomDatabase : RoomDatabase() {
        abstract fun movieDao(): MovieDao
        abstract fun genreDao(): GenreDao
    }

    fun getDatabase(context: Context) : BollyMoviesRoomDatabase {
        return Room.databaseBuilder(
            context,
            BollyMoviesRoomDatabase::class.java, "bollymovies_db"
        ).build()
    }
}
