package com.example.bollymovies.features.home.paging

import androidx.paging.PageKeyedDataSource
import com.example.bollymovies.features.home.repository.HomeRepository
import com.example.bollymovies.features.home.usecase.HomeUseCase
import com.example.bollymovies.model.Result
import com.example.bollymovies.model.TopRated
import com.example.bollymovies.utils.ConstantsApp
import com.example.bollymovies.utils.ResponseApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopRatedPageKeyedDataSource(
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase
) : PageKeyedDataSource<Int, Result>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val movies: List<Result> = callTopRatedMoviesApi(ConstantsApp.Home.FIRST_PAGE)
            callback.onResult(movies, null, ConstantsApp.Home.FIRST_PAGE + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        loadData(params.key, params.key - 1, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        loadData(params.key, params.key + 1, callback)
    }

    private fun loadData(page: Int, nextPage: Int, callback: LoadCallback<Int, Result>) {
        CoroutineScope(Dispatchers.IO).launch {
            val movies: List<Result> = callTopRatedMoviesApi(page)
            callback.onResult(movies, nextPage)
        }
    }

    private suspend fun callTopRatedMoviesApi(page: Int): List<Result> {
        return when (
            val response = homeRepository.getTopRatedMovies(page)
        ) {
            is ResponseApi.Success -> {
                val list = response.data as? TopRated
                homeUseCase.setupTopRatedMoviesList(list)
            }
            is ResponseApi.Error -> {
                listOf()
            }
        }
    }

}