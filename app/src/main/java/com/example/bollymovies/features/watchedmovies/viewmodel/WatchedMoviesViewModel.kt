package com.example.bollymovies.features.watchedmovies.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bollymovies.base.BaseViewModel
import com.example.bollymovies.database.MoviesList
import com.example.bollymovies.database.WatchedMoviesList
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.watchedmovies.usecase.WatchedMoviesUseCase
import kotlinx.coroutines.launch

class WatchedMoviesViewModel(
    application: Application
): BaseViewModel(application) {
    private val watchedMoviesUseCase = WatchedMoviesUseCase(application)

    private val _onSuccessWatchedMoviesFromDb: MutableLiveData<List<WatchedMoviesList>> = MutableLiveData()
    val onSuccessWatchedMoviesFromDb: LiveData<List<WatchedMoviesList>>
        get() = _onSuccessWatchedMoviesFromDb

    fun getWatchedMoviesDb() {
        viewModelScope.launch {
            val watchedMoviesMovies = watchedMoviesUseCase.getWatchedMoviesDb()
            _onSuccessWatchedMoviesFromDb.postValue(watchedMoviesMovies)
        }
    }

}
