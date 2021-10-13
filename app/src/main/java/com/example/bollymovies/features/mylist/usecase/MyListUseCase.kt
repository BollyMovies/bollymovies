package com.example.bollymovies.features.mylist.usecase

import android.app.Application
import com.example.bollymovies.database.MoviesList
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.mylist.repository.MyListRepository

class MyListUseCase(
    private val application: Application
) {
    val myListRepository = MyListRepository(application)

fun buscarFilmes(): List<Movie> {
    val lista = myListRepository.getFakeData()
    return lista
}

    suspend fun getMyListMoviesDb() =
        myListRepository.getMyListMoviesDb()

}