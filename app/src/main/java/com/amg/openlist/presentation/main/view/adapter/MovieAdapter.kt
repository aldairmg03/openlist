package com.amg.openlist.presentation.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amg.openlist.databinding.ItemMovieBinding
import com.amg.openlist.extensions.loadImage
import com.amg.openlist.presentation.main.model.MovieUI

class MovieAdapter(
    private val movies: List<MovieUI>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflate, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieUI) {
            with(binding) {
                imageViewMovie.loadImage(movie.urlPosterPath)
                textViewTitle.text = movie.title
                textViewOverview.text = movie.overview
                textViewReleaseDate.text = movie.releaseDate
            }
        }

    }

}