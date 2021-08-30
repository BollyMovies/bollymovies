package com.example.bollymovies.features.home.usecase

import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.home.repository.HomeRepository


class HomeUseCase {
    private val homeRepository = HomeRepository()

    fun buscarFilmes(): List<Movie> {
        val lista = homeRepository.getFakeData1()
        return lista
    }

    fun buscarFilmes2(): List<Movie> {
        val lista = homeRepository.getFakeData2()
        return lista
    }
}