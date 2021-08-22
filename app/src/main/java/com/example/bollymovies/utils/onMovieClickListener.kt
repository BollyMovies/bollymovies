package com.example.bollymovies.utils

import android.content.Context
import com.example.bollymovies.datamodels.Movie

interface onMovieClickListener {
    fun onClickListener(movie: Movie)
}
