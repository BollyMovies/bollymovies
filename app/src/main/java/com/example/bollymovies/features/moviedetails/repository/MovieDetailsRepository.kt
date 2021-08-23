package com.example.bollymovies.features.moviedetails.repository

import com.example.bollymovies.R
import com.example.bollymovies.datamodels.Streaming

class MovieDetailsRepository {
    fun getFakeData(): List<Streaming>{
        var stream1 = Streaming(
            R.drawable.ic_icons8_aplicativo_de_desktop_netflix
        )
        var stream2 = Streaming(
            R.drawable.ic_icons8_netflix
        )
        var listaDeStreaming = listOf(stream1, stream2)
        return listaDeStreaming
    }

}
