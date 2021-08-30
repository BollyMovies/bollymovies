package com.example.bollymovies.features.moviedetails.usecase

import com.example.bollymovies.model.Streaming
import com.example.bollymovies.features.moviedetails.repository.MovieDetailsRepository

class MovieDetailsUseCase {
    val movieDetailsRepository = MovieDetailsRepository()

    fun buscarStreamings(): List<Streaming> {
        val lista = movieDetailsRepository.getFakeData()
        return lista
    }
}

