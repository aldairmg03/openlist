package com.amg.openlist.domain.usecase

import com.amg.openlist.data.local.entities.MovieEntity
import com.amg.openlist.data.repository.MovieRepository
import javax.inject.Inject

class SaveMoviesInLocalDBUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun invoke(movies: List<MovieEntity>) = movieRepository.saveMovies(movies)

}