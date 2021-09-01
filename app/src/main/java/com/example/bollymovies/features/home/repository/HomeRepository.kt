package com.example.bollymovies.features.home.repository

import com.example.bollymovies.R
import com.example.bollymovies.datamodels.Movie


class HomeRepository {

    fun getFakeData1(): List<Movie> {
        val movie1 = Movie (
            titulo = "White Tiger",
            capa = R.drawable.white_tiger,
            sinopse = "filme indiano",
        )

        val movie2 = Movie (
            titulo = "Lion",
            capa = R.drawable.lion,
            sinopse = "filme indiano",
        )

        val movie3 = Movie (
            titulo = "Dangal",
            capa = R.drawable.dangal,
            sinopse = "filme indiano",
        )

        val movie4= Movie (
            titulo = "Baaghi",
            capa = R.drawable.baaghi,
            sinopse = "filme indiano",
        )

        val movie5 = Movie (
            titulo = "Bahubali",
            capa = R.drawable.bahubali,
            sinopse = "filme indiano",
        )

        val movie6 = Movie (
            titulo = "Pad Man",
            capa = R.drawable.pad_man,
            sinopse = "filme indiano",
        )

        val myList = listOf(movie1, movie2, movie3, movie4, movie5, movie6)
        return myList
    }

    fun getFakeData2(): List<Movie> {
        val movie1 = Movie(
            titulo = "Bahubali",
            capa = R.drawable.bahubali,
            sinopse = "filme indiano",
        )
        val movie2 = Movie(
            titulo = "Pad Man",
            capa = R.drawable.pad_man,
            sinopse = "filme indiano"
        )
        val movie3 = Movie(
            titulo = "White Tiger",
            capa = R.drawable.white_tiger,
            sinopse = "filme indiano",
        )

        val movie4 = Movie(
            titulo = "Lion",
            capa = R.drawable.lion,
            sinopse = "filme indiano",
        )

        val movie5 = Movie(
            titulo = "Dangal",
            capa = R.drawable.dangal,
            sinopse = "filme indiano",
        )

        val movie6 = Movie(
            titulo = "Baaghi",
            capa = R.drawable.baaghi,
            sinopse = "filme indiano",
        )

        val myList = listOf(movie1, movie2, movie3, movie4, movie5, movie6)
        return myList
    }
}