package com.example.bollymovies.features.home.paging

import android.app.Application
import androidx.paging.PageKeyedDataSource
import com.example.bollymovies.database.BollyMoviesDataBase
import com.example.bollymovies.extensions.getFirst4Chars
import com.example.bollymovies.features.home.repository.HomeRepository
import com.example.bollymovies.features.home.usecase.HomeUseCase
import com.example.bollymovies.model.NowPlaying
import com.example.bollymovies.model.Popular
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import com.example.bollymovies.model.Result
import com.example.bollymovies.utils.ConstantsApp.Home.FIRST_PAGE
import com.example.bollymovies.utils.ResponseApi

class NowPlayingPageKeyedDataSource(
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase,
    val application: Application
) : PageKeyedDataSource<Int, Result>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        CoroutineScope(IO).launch {
            val movies: List<Result> = callNowPlayingMoviesApi(FIRST_PAGE)
            homeUseCase.saveNowPlayingDb(movies)
            callback.onResult(movies, null, FIRST_PAGE + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        loadData(params.key, params.key - 1, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        loadData(params.key, params.key + 1, callback)
    }

    private fun loadData(page: Int, nextPage: Int, callback: LoadCallback<Int, Result>) {
        CoroutineScope(IO).launch {
            val movies: List<Result> = callNowPlayingMoviesApi(page)
            homeUseCase.saveNowPlayingDb(movies)
            callback.onResult(movies, nextPage)
        }
    }

    private suspend fun callNowPlayingMoviesApi(page: Int): List<Result> {
        return when (
            val response = homeRepository.getNowPlayingMovies(page)
        ) {
            is ResponseApi.Success -> {
                val list = response.data as? NowPlaying
                homeUseCase.setupNowPlayingMoviesList(list).filter {
                    !it.overview.isNullOrBlank()
                    !it.poster_path.isNullOrBlank()

                }
            }
            is ResponseApi.Error -> {
                var bollyMoviesDb = BollyMoviesDataBase
                    .getDatabase(application)
                    .moviesHomeDao()
                    .getNowPlaying()

                return bollyMoviesDb.map {
                    it
                }
            }
        }
    }
}