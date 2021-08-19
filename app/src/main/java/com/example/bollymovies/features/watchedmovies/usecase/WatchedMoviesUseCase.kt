package com.example.bollymovies.features.watchedmovies.usecase

import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.watchedmovies.repository.WatchedMoviesRepository

class WatchedMoviesUseCase {
    val watchedMoviesRepository = WatchedMoviesRepository()

    fun buscarFilmes(): List<Movie> {
        val lista = watchedMoviesRepository.getFakeData()
        return lista
    }
}