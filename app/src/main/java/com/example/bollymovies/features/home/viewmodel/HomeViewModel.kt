package com.example.bollymovies.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.home.usecase.HomeUseCase

class HomeViewModel: ViewModel() {
    private val homeUseCase = HomeUseCase()

    private val mutableLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    fun buscarLancamentos(): LiveData<List<Movie>> {
        mutableLiveData.value = homeUseCase.buscarFilmes()
        return mutableLiveData
    }

    fun buscarPopular(): LiveData<List<Movie>> {
        mutableLiveData.value = homeUseCase.buscarFilmes()
        return mutableLiveData
    }

    fun buscarSugestion(): LiveData<List<Movie>> {
        mutableLiveData.value = homeUseCase.buscarFilmes2()
        return mutableLiveData
    }

}