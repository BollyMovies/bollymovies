package com.example.bollymovies.features.moviedetails.usecase

import android.app.Application
import com.example.bollymovies.extensions.getFullImageUrl
import com.example.bollymovies.model.Streaming
import com.example.bollymovies.features.moviedetails.repository.MovieDetailsRepository
import com.example.bollymovies.model.Movie
import com.example.bollymovies.utils.ResponseApi

class MovieDetailsUseCase(
    var application: Application
) {

    private val movieDetailsRepository = MovieDetailsRepository(application)

    suspend fun getMovieById(movieId: Int?): ResponseApi {
        return when(val responseApi = movieDetailsRepository.getMovieById(movieId)) {
            is ResponseApi.Success -> {
                val movie = responseApi.data as? Movie
                movie?.backdrop_path = movie?.backdrop_path?.getFullImageUrl()
                movie?.poster_path = movie?.poster_path?.getFullImageUrl()
                return ResponseApi.Success(movie)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }

    suspend fun getMovieByIdFromDb(movieId: Int) =
        movieDetailsRepository.getMovieByIdFromDb(movieId)

}
