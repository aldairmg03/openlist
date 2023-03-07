package com.amg.openlist.presentation.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amg.openlist.databinding.ItemMovieBinding
import com.amg.openlist.extensions.loadImage
import com.amg.openlist.presentation.main.model.MovieUI

class MovieAdapter : ListAdapter<MovieUI, MovieAdapter.MovieViewHolder>(DiffCallbacks) {

    companion object DiffCallbacks : DiffUtil.ItemCallback<MovieUI>() {
        override fun areItemsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflate, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

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