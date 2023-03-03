package com.amg.openlist.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amg.openlist.constants.MovieConstants
import com.amg.openlist.data.remote.Result
import com.amg.openlist.domain.usecase.GetRemoteMoviesByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getRemoteMoviesByCategoryUseCase: GetRemoteMoviesByCategoryUseCase
) : ViewModel() {

    fun getTopRatedMovies() {
        viewModelScope.launch {
            when (val result =
                getRemoteMoviesByCategoryUseCase.invoke(MovieConstants.CATEGORY_TOP_RATED)) {
                is Result.Error -> {
                    Log.e("MovieViewModel", "error", result.exception)
                }
                is Result.Success -> {
                    Log.i("MovieViewModel", "${result.data.size}")
                }
            }
        }
    }

}