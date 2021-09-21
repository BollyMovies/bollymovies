package com.example.bollymovies.features.home.repository

import android.app.Application
import com.example.bollymovies.api.ApiService
import com.example.bollymovies.base.BaseRepository
import com.example.bollymovies.database.BollyMoviesDatabase
import com.example.bollymovies.extensions.toGenreDb
import com.example.bollymovies.extensions.toMovieDb
import com.example.bollymovies.model.Genre
import com.example.bollymovies.model.Result
import com.example.bollymovies.modeldb.GenreDb
import com.example.bollymovies.modeldb.MovieDb
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

    suspend fun getGenres(): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApi.getGenres()
        }
    }

    suspend fun saveGenresDatabase(genres: List<Genre>?) {
        genres?.let { genresApi ->
            val genresDb: MutableList<GenreDb> = mutableListOf()

            genresApi.forEach {
                genresDb.add(it.toGenreDb())
            }

            BollyMoviesDatabase
                .getDatabase(application)
                .genreDao()
                .insertAllGenres(
                    genresDb
                )
        }
    }

    suspend fun saveMoviesDb(movies: List<Result>) {
        val moviesDb: MutableList<MovieDb> = mutableListOf()

        movies.forEach {
            moviesDb.add(it.toMovieDb())
        }

        BollyMoviesDatabase
            .getDatabase(application)
            .movieDao()
            .insertAllMovies(
                moviesDb
            )
    }
}