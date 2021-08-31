package com.example.bollymovies.features.home.viewmodel

import androidx.lifecycle.LiveData
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

class HomeViewModel : BaseViewModel() {

    var nowPlayingPagedList: LiveData<PagedList<Result>>? = null
    var popularPagedList: LiveData<PagedList<Result>>? = null
    var topRatedPagedList: LiveData<PagedList<Result>>? = null
    private var watchMoviesLiveDataSource: LiveData<PageKeyedDataSource<Int, Result>>? = null
    private val homeUseCase = HomeUseCase()
    private val homeRepository = HomeRepository()

    init {
        nowPlayingData()
        popularData()
        topRatedData()
//        val pagedListConfig = PagedList.Config.Builder()
//            .setEnablePlaceholders(false)
//            .setPageSize(PAGE_SIZE).build()
//
//
//        val nowPlayingPageKeyedDataSource = NowPlayingPageKeyedDataSource(
//            homeUseCase = homeUseCase,
//            homeRepository = homeRepository
//        )
//
//        val popularPageKeyedDataSource = PopularPageKeyedDataSource(
//            homeUseCase = homeUseCase,
//            homeRepository = homeRepository
//        )
//
//        val topRatedPageKeyedSource = TopRatedPageKeyedSource(
//            homeUseCase = homeUseCase,
//            homeRepository = homeRepository
//        )
//
//
//        val nowPlayingDataSourceFactory = NowPlayingDataSourceFactory(nowPlayingPageKeyedDataSource)
//        val popularDataSourceFactory = PopularDataSourceFactory(popularPageKeyedDataSource)
//        val topRatedDataSourceFactory = TopRatedDataSourceFactory(topRatedPageKeyedSource)
//
//        watchMoviesLiveDataSource = nowPlayingDataSourceFactory.getLiveDataSource()
//        nowPlayingPagedList = LivePagedListBuilder(nowPlayingDataSourceFactory, pagedListConfig)
//            .build()
//
//        watchMoviesLiveDataSource = popularDataSourceFactory.getLiveDataSource()
//        popularPagedList = LivePagedListBuilder(popularDataSourceFactory, pagedListConfig)
//            .build()
//
//        watchMoviesLiveDataSource = topRatedDataSourceFactory.getLiveDataSource()
//        topRatedPagedList = LivePagedListBuilder(topRatedDataSourceFactory, pagedListConfig)
//            .build()


    }

    fun nowPlayingData(){
        val nowPlayingPageKeyedDataSource = NowPlayingPageKeyedDataSource(
            homeUseCase = homeUseCase,
            homeRepository = homeRepository
        )
        val nowPlayingDataSourceFactory = NowPlayingDataSourceFactory(nowPlayingPageKeyedDataSource)
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()

        watchMoviesLiveDataSource = nowPlayingDataSourceFactory.getLiveDataSource()
        nowPlayingPagedList = LivePagedListBuilder(nowPlayingDataSourceFactory, pagedListConfig)
            .build()
    }

    fun popularData(){
        val popularPageKeyedDataSource = PopularPageKeyedDataSource(
            homeUseCase = homeUseCase,
            homeRepository = homeRepository
        )
        val popularDataSourceFactory = PopularDataSourceFactory(popularPageKeyedDataSource)
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE).build()

        watchMoviesLiveDataSource = popularDataSourceFactory.getLiveDataSource()
        popularPagedList = LivePagedListBuilder(popularDataSourceFactory, pagedListConfig)
            .build()
    }

    fun topRatedData(){
        val topRatedPageKeyedDataSource = TopRatedPageKeyedDataSource(
            homeUseCase = homeUseCase,
            homeRepository = homeRepository
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