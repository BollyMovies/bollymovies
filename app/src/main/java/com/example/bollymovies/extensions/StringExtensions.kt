package com.example.bollymovies.extensions

fun String.getFullImageUrl() = "https://image.tmdb.org/t/p/w500$this"

fun String?.getFullYoutubeUrl() = "https://www.youtube.com/watch?v=$this"

fun String.getFirst4Chars(): String {
    return "${this[0]}${this[1]}${this[2]}${this[3]}"
}