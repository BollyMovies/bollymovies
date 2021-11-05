package com.example.bollymovies.features.watchedmovies.repository

import android.app.Application
import com.example.bollymovies.R
import com.example.bollymovies.database.BollyMoviesDataBase


class WatchedMoviesRepository(
    private val application: Application
) {
    suspend fun getWatchedMoviesDb() =
        BollyMoviesDataBase.getDatabase(application)
            .watchedMoviesListDao().getAllFavorites()
}