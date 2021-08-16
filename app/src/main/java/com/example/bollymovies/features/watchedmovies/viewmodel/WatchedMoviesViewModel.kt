package com.example.bollymovies.features.watchedmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.watchedmovies.usecase.WatchedMoviesFakeData

class WatchedMoviesViewModel: ViewModel() {
    private val mutableLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    fun buscarFilmes(): LiveData<List<Movie>> {
        mutableLiveData.value = WatchedMoviesFakeData().getLocalData()
        return mutableLiveData
    }
}
