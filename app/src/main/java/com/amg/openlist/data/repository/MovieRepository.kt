package com.amg.openlist.data.repository

import com.amg.openlist.data.local.MovieDao
import com.amg.openlist.data.local.entities.MovieEntity
import com.amg.openlist.data.remote.MovieRemoteDataSource
import com.amg.openlist.data.remote.Result
import com.amg.openlist.data.remote.model.MovieResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieDao
) {

    suspend fun getMoviesByCategory(category: String): Result<List<MovieResponse>> =
        remoteDataSource.getMoviesByCategory(category)

    suspend fun getMoviesLocalByCategory(category: String) =
        localDataSource.getMoviesByCategory(category)

    suspend fun saveMovies(movies: List<MovieEntity>) = localDataSource.insertAll(movies)

}