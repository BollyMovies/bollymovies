package com.example.bollymovies.features.mylist.repository

import android.app.Application
import com.example.bollymovies.R
import com.example.bollymovies.database.BollyMoviesDataBase
import com.example.bollymovies.database.MoviesList


class MyListRepository(
    private val application: Application
) {
    suspend fun getMyListMoviesDb() =
        BollyMoviesDataBase.getDatabase(application)
            .moviesListDao().getAllFavorites()

}