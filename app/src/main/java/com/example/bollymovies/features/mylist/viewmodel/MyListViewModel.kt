package com.example.bollymovies.features.mylist.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bollymovies.base.BaseViewModel
import com.example.bollymovies.database.MoviesList
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.mylist.usecase.MyListUseCase
import kotlinx.coroutines.launch

class MyListViewModel(
    application: Application
) : BaseViewModel(application) {
    private val myListUseCase = MyListUseCase(application)

    private val _onSucessMyListFromDb: MutableLiveData<List<MoviesList>> = MutableLiveData()
    val onSucessMyListFromDb: LiveData<List<MoviesList>>
        get() = _onSucessMyListFromDb

    fun getMyListMoviesDb() {
        viewModelScope.launch {
            val myListMovies = myListUseCase.getMyListMoviesDb()
            _onSucessMyListFromDb.postValue(myListMovies)
        }
    }
}

