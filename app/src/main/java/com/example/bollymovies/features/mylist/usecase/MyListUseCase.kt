package com.example.bollymovies.features.mylist.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.mylist.repository.MyListFakeData

class MyListUseCase {
    val myListFakeData = MyListFakeData()

fun buscarFilmes(): List<Movie> {
    val lista = myListFakeData.getLocalData()
    return lista
}
}