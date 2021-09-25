package com.example.bollymovies.model

import android.content.Context
import com.example.bollymovies.R

data class MovieCredits(
    val cast: List<Cast>?,
    val crew: List<Crew>?,
    val id: Int
) {
    fun getCastName(context: Context): String {
        return if (cast?.size != 0 && cast?.size != null && cast.size >= 3) {
            val cast1 = cast.elementAt(0).name
            val cast2 = cast.elementAt(1).name
            val cast3 = cast.elementAt(2).name
            val castLabel: String = context.getString(R.string.cast)

            "$castLabel $cast1, $cast2, $cast3"

        } else if(cast?.size != 0 && cast?.size != null && cast.size == 2) {
            val cast1 = cast.elementAt(0).name
            val cast2 = cast.elementAt(1).name
            val castLabel: String = context.getString(R.string.cast)

            "$castLabel $cast1, $cast2"

        } else if(cast?.size != 0 && cast?.size != null && cast.size == 1) {
            val cast1 = cast.elementAt(0).name
            val castLabel: String = context.getString(R.string.cast)

            "$castLabel $cast1"
        }
        else {
            context.getString(R.string.cast_not_found)
        }

    }

}
