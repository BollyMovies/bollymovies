package com.example.bollymovies.features.mylist.repository

import android.app.Application
import com.example.bollymovies.R
import com.example.bollymovies.database.BollyMoviesDataBase
import com.example.bollymovies.database.MoviesList
import com.example.bollymovies.datamodels.Movie

class MyListRepository(
    private val application: Application
) {
    suspend fun getMyListMoviesDb() =
        BollyMoviesDataBase.getDatabase(application)
            .moviesListDao().getAllFavorites()

}