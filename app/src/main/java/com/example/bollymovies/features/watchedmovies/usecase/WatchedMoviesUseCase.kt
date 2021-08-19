package com.example.bollymovies.features.watchedmovies.usecase

import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.mylist.repository.MyListFakeData
import com.example.bollymovies.features.watchedmovies.repository.WatchedMoviesFakeData

class WatchedMoviesUseCase {
    val watchedMoviesFakeData = WatchedMoviesFakeData()

    fun buscarFilmes(): List<Movie> {
        val lista = watchedMoviesFakeData.getLocalData()
        return lista
    }
}