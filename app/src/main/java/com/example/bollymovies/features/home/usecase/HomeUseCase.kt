package com.example.bollymovies.features.home.usecase

import com.example.bollymovies.extensions.getFullImageUrl
import com.example.bollymovies.features.home.repository.HomeRepository
import com.example.bollymovies.model.NowPlaying
import com.example.bollymovies.model.Popular
import com.example.bollymovies.model.Result
import com.example.bollymovies.model.TopRated


class HomeUseCase {

    private val homeRepository = HomeRepository()

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

}