package com.example.bollymovies.features.moviedetails.repository

import android.app.Application
import com.example.bollymovies.R
import com.example.bollymovies.api.ApiService
import com.example.bollymovies.base.BaseRepository
import com.example.bollymovies.database.BollyMoviesDataBase
import com.example.bollymovies.database.MoviesList
import com.example.bollymovies.model.Streaming
import com.example.bollymovies.utils.ResponseApi

class MovieDetailsRepository(
    var application: Application
): BaseRepository() {

    suspend fun getMovieById(movieId: Int?): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApiWithCast.getMovieById(movieId)
        }
    }

    suspend fun getMovieByIdFromDb(movieId: Int) =
        BollyMoviesDataBase.getDatabase(application)
            .moviesHomeDao().getMovieById(movieId)

    suspend fun saveMyListMovieDb(movie: MoviesList) =
        BollyMoviesDataBase.getDatabase(application)
            .moviesListDao().insertFavorites(movie)

    suspend fun deleteMyListMovieDb(movie: MoviesList) =
        BollyMoviesDataBase.getDatabase(application)
            .moviesListDao().delete(movie)

}
