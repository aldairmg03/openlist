package com.amg.openlist.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = [("id")])
data class MovieEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val poster_path: String?,
    var category: String,
)