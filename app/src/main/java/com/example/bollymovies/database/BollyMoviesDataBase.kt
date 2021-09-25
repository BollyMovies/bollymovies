package com.example.bollymovies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bollymovies.model.Result


object BollyMoviesDataBase {

    @Database(entities = [Result::class], version = 1, exportSchema = false)
    abstract class BollyMoviesRoomDatabase : RoomDatabase() {
        abstract fun moviesHomeDao(): MoviesHomeDao
    }

    fun getDatabase(context: Context) : BollyMoviesRoomDatabase {
        return Room.databaseBuilder(
            context,
            BollyMoviesRoomDatabase::class.java, "bollymovies_db"
        ).build()
    }
}