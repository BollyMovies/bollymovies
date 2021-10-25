package com.example.bollymovies.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Videos(
    val results: List<ResultVideos>
): Parcelable