package com.example.bollymovies.features.home.usecase

import android.app.Application
import com.example.bollymovies.extensions.getFullImageUrl
import com.example.bollymovies.features.home.repository.HomeRepository
import com.example.bollymovies.model.*
import com.example.bollymovies.utils.ResponseApi


class HomeUseCase(
    private val application: Application
) {

    private val homeRepository = HomeRepository(application)

    suspend fun getMovieById(id: Int) =
        homeRepository.getMovieById(id)

    fun setupNowPlayingMoviesList(list: NowPlaying?): List <Result> {
        return list?.results?.map {
            it.backdrop_path = it.backdrop_path?.getFullImageUrl()
            it.poster_path = it.poster_path?.getFullImageUrl()
            it
        } ?: listOf()
    }

    fun setupPopularMoviesList(list: Popular?): List <Result> {
        return list?.results?.map {
            it.backdrop_path = it.backdrop_path?.getFullImageUrl()
            it.poster_path = it.poster_path?.getFullImageUrl()
            it
        } ?: listOf()
    }

    fun setupTopRatedMoviesList(list: TopRated?): List <Result> {
        return list?.results?.map {
            it.backdrop_path = it.backdrop_path?.getFullImageUrl()
            it.poster_path = it.poster_path?.getFullImageUrl()
            it
        } ?: listOf()
    }

    suspend fun getGenres(): ResponseApi {
        return when(val response = homeRepository.getGenres()) {
            is ResponseApi.Success -> {
                val genreInfo = response.data as? GenreInfo
                homeRepository.saveGenresDatabase(genreInfo?.genres)
                response
            }
            is ResponseApi.Error -> {
                response
            }
        }

    }

    suspend fun saveMoviesDb(movies: List<Result>) {
        homeRepository.saveMoviesDb(movies)
    }
}