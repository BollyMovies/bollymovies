package com.example.bollymovies.features.home.repository

import android.app.Application
import com.example.bollymovies.api.ApiService
import com.example.bollymovies.base.BaseRepository
import com.example.bollymovies.database.BollyMoviesDataBase
import com.example.bollymovies.model.Result
import com.example.bollymovies.utils.ResponseApi


class HomeRepository(
    val application: Application
) : BaseRepository() {

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

    suspend fun saveNowPlayingDb(movies: List<Result>) {
        val nowPlayingDb: MutableList<Result> = mutableListOf()

        movies.forEach {
            nowPlayingDb.add(it)
        }

        BollyMoviesDataBase
            .getDatabase(application)
            .moviesHomeDao()
            .insertAllMovies(
                nowPlayingDb
            )
    }

    suspend fun savePopularDb(movies: List<Result>) {
        val popularDb: MutableList<Result> = mutableListOf()

        movies.forEach {
            popularDb.add(it)
        }

        BollyMoviesDataBase
            .getDatabase(application)
            .moviesHomeDao()
            .insertAllMovies(
                popularDb
            )
    }

    suspend fun saveTopRatedDb(movies: List<Result>) {
        val topRatedDb: MutableList<Result> = mutableListOf()

        movies.forEach {
            topRatedDb.add(it)
        }

        BollyMoviesDataBase
            .getDatabase(application)
            .moviesHomeDao()
            .insertAllMovies(
                topRatedDb
            )
    }
}