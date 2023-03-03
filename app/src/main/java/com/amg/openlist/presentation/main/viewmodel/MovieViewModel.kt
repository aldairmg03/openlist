package com.amg.openlist.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amg.openlist.constants.MovieConstants
import com.amg.openlist.data.remote.Result
import com.amg.openlist.domain.usecase.GetRemoteMoviesByCategoryUseCase
import com.amg.openlist.presentation.main.model.MovieUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getRemoteMoviesByCategoryUseCase: GetRemoteMoviesByCategoryUseCase
) : ViewModel() {

    private val _moviesMLD = MutableLiveData<List<MovieUI>>()
    val onMovies: LiveData<List<MovieUI>> get() = _moviesMLD

    private val _showMessageMLD = MutableLiveData<String>()
    val onShowMessage: LiveData<String> get() = _showMessageMLD

    fun getTopRatedMovies() {
        viewModelScope.launch {
            when (val result =
                getRemoteMoviesByCategoryUseCase.invoke(MovieConstants.CATEGORY_TOP_RATED)) {
                is Result.Error -> {
                    _showMessageMLD.value = result.exception.message ?: ""
                }
                is Result.Success -> {
                    val movies = result.data.map { movieResponse ->
                        val posterPath =
                            movieResponse.posterPath?.let { MovieConstants.getPosterPath(it) } ?: ""
                        MovieUI(
                            movieResponse.id,
                            movieResponse.title,
                            movieResponse.overview,
                            movieResponse.releaseDate,
                            posterPath
                        )
                    }
                    _moviesMLD.value = movies
                }
            }
        }
    }

}