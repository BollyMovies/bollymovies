package com.example.bollymovies.api

import com.example.bollymovies.model.Movie
import com.example.bollymovies.model.NowPlaying
import com.example.bollymovies.model.Popular
import com.example.bollymovies.model.TopRated
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface TMDBApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int?
    ): Response<NowPlaying>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query ("page") page: Int?
    ): Response<Popular>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query ("page") page: Int?
    ): Response<TopRated>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int?
    ): Response<Movie>

    @POST("movie/save")
    suspend fun saveMovie(
        @Body movie: Movie
    ): Response<ResponseBody>
}