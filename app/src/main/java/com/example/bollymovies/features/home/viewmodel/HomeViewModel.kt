package com.example.bollymovies.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.home.usecase.HomeUseCase

class HomeViewModel: ViewModel() {

    private val homeUseCase = HomeUseCase()

    private val mutableLiveDataPopular: MutableLiveData<List<Movie>> = MutableLiveData()
    private val mutableLiveDataLancamentos: MutableLiveData<List<Movie>> = MutableLiveData()
    private val mutableLiveDataSugestion: MutableLiveData<List<Movie>> = MutableLiveData()

    fun buscarLancamentos(): LiveData<List<Movie>> {
        mutableLiveDataLancamentos.value = homeUseCase.buscarFilmes()
        return mutableLiveDataLancamentos
    }

    fun buscarPopular(): LiveData<List<Movie>> {
        mutableLiveDataPopular.value = homeUseCase.buscarFilmes2()
        return mutableLiveDataPopular
    }

    fun buscarSugestion(): LiveData<List<Movie>> {
        mutableLiveDataSugestion.value = homeUseCase.buscarFilmes()
        return mutableLiveDataSugestion
    }
}