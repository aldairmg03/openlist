package com.amg.openlist.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amg.openlist.constants.MovieConstants
import com.amg.openlist.data.local.entities.MovieEntity
import com.amg.openlist.data.remote.Result
import com.amg.openlist.data.remote.model.MovieResponse
import com.amg.openlist.domain.usecase.GetLocalMoviesByCategoryUseCase
import com.amg.openlist.domain.usecase.GetRemoteMoviesByCategoryUseCase
import com.amg.openlist.domain.usecase.SaveMoviesInLocalDBUseCase
import com.amg.openlist.presentation.main.model.MovieUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getRemoteMoviesByCategoryUseCase: GetRemoteMoviesByCategoryUseCase,
    private val getLocalMoviesByCategoryUseCase: GetLocalMoviesByCategoryUseCase,
    private val saveMoviesInLocalDBUseCase: SaveMoviesInLocalDBUseCase
) : ViewModel() {

    private val _moviesMLD = MutableLiveData<List<MovieUI>>()
    val onMovies: LiveData<List<MovieUI>> get() = _moviesMLD

    private val _showMessageMLD = MutableLiveData<String>()
    val onShowMessage: LiveData<String> get() = _showMessageMLD

    fun getTopRatedMovies() {
        val topRated = MovieConstants.CATEGORY_TOP_RATED

        viewModelScope.launch {
            val movies = getLocalMoviesByCategoryUseCase.invoke(topRated)
            if (movies.isNotEmpty()) {
                val moviesUI = movies.map { movieEntity ->
                    val posterPath =
                        movieEntity.poster_path?.let { MovieConstants.getPosterPath(it) }
                            ?: ""
                    MovieUI(
                        movieEntity.id,
                        movieEntity.title,
                        movieEntity.overview,
                        movieEntity.release_date,
                        posterPath
                    )
                }
                _moviesMLD.value = moviesUI
            } else {
                getRemoteTopRatedMovies(topRated)
            }
        }
    }

    private fun getRemoteTopRatedMovies(category: String) {
        viewModelScope.launch {
            when (val result =
                getRemoteMoviesByCategoryUseCase.invoke(category)) {
                is Result.Error -> {
                    _showMessageMLD.value = result.exception.message ?: ""
                }
                is Result.Success -> {
                    val moviesResponse = result.data
                    val movies = moviesResponse.map { movieResponse ->
                        val posterPath =
                            movieResponse.posterPath?.let { MovieConstants.getPosterPath(it) }
                                ?: ""
                        MovieUI(
                            movieResponse.id,
                            movieResponse.title,
                            movieResponse.overview,
                            movieResponse.releaseDate,
                            posterPath
                        )
                    }
                    saveMoviesInLocalDB(moviesResponse, category)
                    _moviesMLD.value = movies
                }
            }
        }
    }

    private fun saveMoviesInLocalDB(movies: List<MovieResponse>, category: String) {
        val moviesEntity = movies.map { movieResponse ->
            MovieEntity(
                movieResponse.id,
                movieResponse.title,
                movieResponse.overview,
                movieResponse.releaseDate,
                movieResponse.posterPath,
                category
            )
        }
        viewModelScope.launch {
            saveMoviesInLocalDBUseCase.invoke(moviesEntity)
        }
    }

}