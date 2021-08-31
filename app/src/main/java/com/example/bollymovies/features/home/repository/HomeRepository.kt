package com.example.bollymovies.features.home.repository

import com.example.bollymovies.api.ApiService
import com.example.bollymovies.base.BaseRepository
import com.example.bollymovies.utils.ResponseApi


class HomeRepository : BaseRepository() {

    suspend fun getNowPlayingMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getNowPlayingMovies(page)
        }
    }

    suspend fun getPopularMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getPopularMovies(page)
        }
    }

    suspend fun getTopRatedMovies(page: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getTopRatedMovies(page)
        }
    }

    suspend fun getMovieById(id: Int): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getMovieById(id)
        }
    }
}