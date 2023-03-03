package com.amg.openlist.domain.usecase

import com.amg.openlist.data.remote.Result
import com.amg.openlist.data.remote.model.MovieResponse
import com.amg.openlist.data.repository.MovieRepository
import javax.inject.Inject

class GetRemoteMoviesByCategoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun invoke(category: String): Result<List<MovieResponse>> =
        movieRepository.getMoviesByCategory(category)
}