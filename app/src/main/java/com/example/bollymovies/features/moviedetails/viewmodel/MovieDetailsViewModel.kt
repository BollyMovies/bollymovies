package com.example.bollymovies.features.moviedetails.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bollymovies.base.BaseViewModel
import com.example.bollymovies.model.Streaming
import com.example.bollymovies.features.moviedetails.usecase.MovieDetailsUseCase
import com.example.bollymovies.model.Movie
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    application: Application
): BaseViewModel(application) {
    private val movieDetailUseCase = MovieDetailsUseCase()

    private val _onSuccessMovieById: MutableLiveData<Movie> = MutableLiveData()
    val onSuccessMovieById: LiveData<Movie>
        get() = _onSuccessMovieById

    fun getMovieById(movieId: Int?) {
        viewModelScope.launch {
            callApi(
                suspend { movieDetailUseCase.getMovieById(movieId) },
                onSuccess = {
                    _onSuccessMovieById.postValue(it as? Movie)
                }
            )
        }
    }
}