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
            it.type = 2
            it
        } ?: listOf()
    }

    fun setupPopularMoviesList(list: Popular?): List <Result> {
        return list?.results?.map {
            it.backdrop_path = it.backdrop_path?.getFullImageUrl()
            it.poster_path = it.poster_path?.getFullImageUrl()
            it.type = 1
            it
        } ?: listOf()
    }

    fun setupTopRatedMoviesList(list: TopRated?): List <Result> {
        return list?.results?.map {
            it.backdrop_path = it.backdrop_path?.getFullImageUrl()
            it.poster_path = it.poster_path?.getFullImageUrl()
            it.type = 3
            it
        } ?: listOf()
    }


    suspend fun saveNowPlayingDb(movies: List<Result>) {
        homeRepository.saveNowPlayingDb(movies)
    }

    suspend fun savePopularDb(movies: List<Result>) {
        homeRepository.savePopularDb(movies)
    }

    suspend fun saveTopRatedDb(movies: List<Result>) {
        homeRepository.saveTopRatedDb(movies)
    }
}