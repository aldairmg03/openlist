package com.amg.openlist.data.repository

import com.amg.openlist.data.remote.MovieRemoteDataSource
import com.amg.openlist.data.remote.Result
import com.amg.openlist.data.remote.model.MovieResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) {

    suspend fun getMoviesByCategory(category: String): Result<List<MovieResponse>> =
        remoteDataSource.getMoviesByCategory(category)

}