package com.amg.openlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.amg.openlist.presentation.main.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        getTopRatedMovies()
    }

    private fun initViewModel() {
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    }

    private fun getTopRatedMovies() {
        movieViewModel.getTopRatedMovies()
    }

}