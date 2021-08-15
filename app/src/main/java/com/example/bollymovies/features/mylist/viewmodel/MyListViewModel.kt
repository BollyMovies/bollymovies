package com.example.bollymovies.features.mylist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.mylist.usecase.FakeData

class MyListViewModel: ViewModel() {
    private val mutableLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    fun buscarFilmes(): LiveData<List<Movie>> {
        mutableLiveData.value = FakeData().getLocalData()
        return mutableLiveData
    }
}
