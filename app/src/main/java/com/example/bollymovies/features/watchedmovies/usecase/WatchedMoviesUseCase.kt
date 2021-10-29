package com.example.bollymovies.features.watchedmovies.usecase

import android.app.Application
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.watchedmovies.repository.WatchedMoviesRepository

class WatchedMoviesUseCase(
    private val application: Application
) {
    val watchedMoviesRepository = WatchedMoviesRepository(application)

    suspend fun getWatchedMoviesDb() =
        watchedMoviesRepository.getWatchedMoviesDb()
}