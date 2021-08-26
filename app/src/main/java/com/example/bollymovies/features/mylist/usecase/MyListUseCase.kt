package com.example.bollymovies.features.mylist.usecase

import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.mylist.repository.MyListRepository

class MyListUseCase {
    val myListRepository = MyListRepository()

fun buscarFilmes(): List<Movie> {
    val lista = myListRepository.getFakeData()
    return lista
}
}