package com.example.bollymovies.datamodels

import java.io.Serializable

class Movie(
    val titulo: String,
    val capa: Int,
    val sinopse: String,
): Serializable {
}