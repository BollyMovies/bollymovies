package com.example.bollymovies.features.moviedetails.repository

import com.example.bollymovies.R
import com.example.bollymovies.api.ApiService
import com.example.bollymovies.base.BaseRepository
import com.example.bollymovies.model.Streaming
import com.example.bollymovies.utils.ResponseApi

class MovieDetailsRepository: BaseRepository() {

    suspend fun getMovieById(movieId: Int?): ResponseApi {
        return safeApiCall {
            ApiService.tmdbApiWithCast.getMovieById(movieId)
        }
    }
}
