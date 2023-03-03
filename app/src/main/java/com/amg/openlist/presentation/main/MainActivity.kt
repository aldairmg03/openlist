package com.amg.openlist.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.amg.openlist.databinding.ActivityMainBinding
import com.amg.openlist.presentation.main.model.MovieUI
import com.amg.openlist.presentation.main.view.adapter.MovieAdapter
import com.amg.openlist.presentation.main.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        getTopRatedMovies()
    }

    private fun initViewModel() {
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        with(movieViewModel) {

            onMovies.observe(this@MainActivity) { movies ->
                showData(movies)
            }

            onShowMessage.observe(this@MainActivity) { message ->
                showMessage(message)
            }

            onShowLoading.observe(this@MainActivity) { isShow ->
                showLoading(isShow)
            }
        }
    }

    private fun showData(movies: List<MovieUI>) {
        val movieAdapter = MovieAdapter(movies)
        binding.recyclerViewMovies.adapter = movieAdapter
    }

    private fun getTopRatedMovies() {
        movieViewModel.getTopRatedMovies()
    }

    private fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun showLoading(isShow: Boolean) {
        with(binding) {
            layoutLoading.isVisible = isShow
            progressBarLoading.isIndeterminate = isShow
        }
    }

}