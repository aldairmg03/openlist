package com.amg.openlist.data.remote

import com.amg.openlist.data.remote.model.MovieResponse

interface MovieRemoteDataSource {

    suspend fun getMoviesByCategory(category: String): Result<List<MovieResponse>>

}