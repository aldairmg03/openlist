package com.amg.openlist.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
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
                //showMessage(message)
                showAlert()
            }

            onShowLoading.observe(this@MainActivity) { isShow ->
                showLoading(isShow)
            }
        }
    }

    private fun initViews() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun showData(movies: List<MovieUI>) {
        val movieAdapter = MovieAdapter()
        binding.recyclerViewMovies.adapter = movieAdapter
        movieAdapter.submitList(movies)
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

    private fun showAlert() {
        DialogMessage { dialog, _ ->
            dialog.dismiss()
            movieViewModel.retryGetMovies()
        }.show(supportFragmentManager, "DialogMessage")
    }

}