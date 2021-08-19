package com.example.bollymovies.features.home.usecase

import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.home.repository.HomeFakeData
import com.example.bollymovies.features.home.repository.HomeFakeData2


class HomeUseCase {
    private val homeFakeData = HomeFakeData()
    private val homeFakeData2 = HomeFakeData2()

    fun buscarFilmes(): List<Movie> {
        val lista = homeFakeData.getLocalData()
        return lista
    }

    fun buscarFilmes2(): List<Movie> {
        val lista = homeFakeData2.getLocalData()
        return lista
    }

}