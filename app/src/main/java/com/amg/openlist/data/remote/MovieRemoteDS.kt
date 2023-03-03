package com.amg.openlist.data.remote

import com.amg.openlist.base.BaseDataSource
import com.amg.openlist.data.remote.model.MovieResponse
import javax.inject.Inject

class MovieRemoteDS @Inject constructor(
    private val movieService: MovieService
) : BaseDataSource(), MovieRemoteDataSource {

    override suspend fun getMoviesByCategory(
        category: String
    ): Result<List<MovieResponse>> = getResponse {
        movieService.getMoviesByCategory(category)
    }

}