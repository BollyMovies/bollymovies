package com.example.bollymovies.features.mylist.usecase

import com.example.bollymovies.datamodels.Movie

class FakeData {
    fun getLocalData(): List<Movie> {
        val movie1 = Movie (
            titulo = "Parasita",
            capa = 5,
            sinopse = "filme coreano maluco",
        )

        val movie2 = Movie (
            titulo = "Turma da Mônica",
            capa = 5,
            sinopse = "filme coreano maluco",
        )

        val movie3 = Movie (
            titulo = "Ave Maria",
            capa = 5,
            sinopse = "filme coreano maluco",
        )

        val movie4= Movie (
            titulo = "Neymar Jr",
            capa = 5,
            sinopse = "filme coreano maluco",
        )

        val myList = listOf(movie1, movie2, movie3, movie4)
        return myList
    }
}