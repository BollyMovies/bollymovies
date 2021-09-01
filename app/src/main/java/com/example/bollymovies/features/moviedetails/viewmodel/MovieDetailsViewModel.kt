package com.example.bollymovies.features.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bollymovies.model.Streaming
import com.example.bollymovies.features.moviedetails.usecase.MovieDetailsUseCase

class MovieDetailsViewModel: ViewModel() {
    private val movieDetailsUseCase = MovieDetailsUseCase()

    private val mutableLiveData: MutableLiveData<List<Streaming>> = MutableLiveData()

    fun buscarStreamings(): LiveData<List<Streaming>> {
        mutableLiveData.value = movieDetailsUseCase.buscarStreamings()
        return mutableLiveData
    }
}