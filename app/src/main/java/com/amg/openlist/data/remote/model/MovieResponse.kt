package com.amg.openlist.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterPath: String?
)