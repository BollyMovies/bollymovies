package com.example.bollymovies.features.moviedetails.usecase

import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.datamodels.Streaming
import com.example.bollymovies.features.moviedetails.repository.MovieDetailsRepository
import com.example.bollymovies.features.mylist.repository.MyListRepository

class MovieDetailsUseCase {
    val movieDetailsRepository = MovieDetailsRepository()

    fun buscarStreamings(): List<Streaming> {
        val lista = movieDetailsRepository.getFakeData()
        return lista
    }
}

