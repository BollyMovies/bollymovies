package com.example.bollymovies.features.home.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.bollymovies.base.BaseViewModel
import com.example.bollymovies.features.home.paging.*
import com.example.bollymovies.features.home.repository.HomeRepository
import com.example.bollymovies.features.home.usecase.HomeUseCase
import com.example.bollymovies.model.Result
import com.example.bollymovies.utils.ConstantsApp.Home.PAGE_SIZE
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application
) : BaseViewModel(application) {

    var nowPlayingPagedList: LiveData<PagedList<Result>>? = null
    var popularPagedList: LiveData<PagedList<Result>>? = null
    var topRatedPagedList: LiveData<PagedList<Result>>? = null
    private var watchMoviesLiveDataSource: LiveData<PageKeyedDataSource<Int, Result>>? = null
    private val homeUseCase = HomeUseCase(getApplication())
    private val homeRepository = HomeRepository(getApplication<Application>())


    init {
        nowPlayingData(application)
        popularData(application)
        topRatedData(application)
    }

    fun nowPlayingData(application: Application){
        val nowPlayingPageKeyedDataSource = NowPlayingPageKeyedDataSource(
            homeUseCase = homeUseCase,
            homeRepository = homeRepository,
            application = application
        )
        val nowPlayingDataSourceFactory = NowPlayingDataSourceFactory(nowPlayingPageKeyedDataSource)
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()

        watchMoviesLiveDataSource = nowPlayingDataSourceFactory.getLiveDataSource()
        nowPlayingPagedList = LivePagedListBuilder(nowPlayingDataSourceFactory, pagedListConfig)
            .build()
    }

    fun popularData(application: Application){
        val popularPageKeyedDataSource = PopularPageKeyedDataSource(
            homeUseCase = homeUseCase,
            homeRepository = homeRepository,
            application = application
        )
        val popularDataSourceFactory = PopularDataSourceFactory(popularPageKeyedDataSource)
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()

        watchMoviesLiveDataSource = popularDataSourceFactory.getLiveDataSource()
        popularPagedList = LivePagedListBuilder(popularDataSourceFactory, pagedListConfig)
            .build()
    }

    fun topRatedData(application: Application){
        val topRatedPageKeyedDataSource = TopRatedPageKeyedDataSource(
            homeUseCase = homeUseCase,
            homeRepository = homeRepository,
            application = application
        )
        val topRatedDataSourceFactory = TopRatedDataSourceFactory(topRatedPageKeyedDataSource)
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()

        watchMoviesLiveDataSource = topRatedDataSourceFactory.getLiveDataSource()
        topRatedPagedList = LivePagedListBuilder(topRatedDataSourceFactory, pagedListConfig)
            .build()
    }

    fun getMovieById(id: Int) {
        viewModelScope.launch {
            callApi(
                suspend { homeUseCase.getMovieById(id) },
                onSuccess = {
                    it
                }
            )
        }
    }
}