package com.example.bollymovies.features.moviedetails.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bollymovies.base.BaseViewModel
import com.example.bollymovies.database.MoviesList
import com.example.bollymovies.database.WatchedMoviesList
import com.example.bollymovies.features.moviedetails.usecase.MovieDetailsUseCase
import com.example.bollymovies.model.Movie
import com.example.bollymovies.model.Result
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    application: Application
) : BaseViewModel(application) {
    private val movieDetailUseCase = MovieDetailsUseCase(application)

    private val _onSuccessMovieById: MutableLiveData<Movie> = MutableLiveData()
    val onSuccessMovieById: LiveData<Movie>
        get() = _onSuccessMovieById

    private val _onSuccessMovieByIdFromDb: MutableLiveData<Result> = MutableLiveData()
    val onSucessMovieByIdeFromDb: LiveData<Result>
        get() = _onSuccessMovieByIdFromDb

    private val _onSuccessMyListFromDb: MutableLiveData<List<MoviesList>> = MutableLiveData()
    val onSuccessMyListFromDb: LiveData<List<MoviesList>>
        get() = _onSuccessMyListFromDb

    private val _onSuccessWatchedFromDb: MutableLiveData<List<WatchedMoviesList>> = MutableLiveData()
    val onSuccessWatchedFromDb: LiveData<List<WatchedMoviesList>>
        get() = _onSuccessWatchedFromDb


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

    fun getMovieByIdFromDb(movieId: Int) {
        viewModelScope.launch {
            val movieFromDb = movieDetailUseCase.getMovieByIdFromDb(movieId)
            _onSuccessMovieByIdFromDb.postValue(movieFromDb)

        }
    }

    fun saveMyListMovieDb(movie: MoviesList) {
        viewModelScope.launch {
            movieDetailUseCase.saveMyListMovie(movie)
        }
    }

    fun deleteMyListMovieDb(movie: MoviesList) {
        viewModelScope.launch {
            movieDetailUseCase.deleteMyListMovieDb(movie)
        }
    }

    fun getMyListMoviesDb() {
        viewModelScope.launch {
            val myListMovies = movieDetailUseCase.getMyListMoviesDb()
            _onSuccessMyListFromDb.postValue(myListMovies)
        }
    }

    fun saveWatchedMovieDb(movie: WatchedMoviesList) {
        viewModelScope.launch {
            movieDetailUseCase.saveWatchedMovie(movie)
        }
    }

    fun deleteWatchedMovieDb(movie: WatchedMoviesList) {
        viewModelScope.launch {
            movieDetailUseCase.deleteWatchedMovieDb(movie)
        }
    }

    fun getWatchedMoviesDb() {
        viewModelScope.launch {
            val myListMovies = movieDetailUseCase.getWatchedMoviesDb()
            _onSuccessWatchedFromDb.postValue(myListMovies)
        }
    }
}
