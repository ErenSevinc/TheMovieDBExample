package com.example.themoviedbexample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themoviedbexample.databinding.ItemViewMovieBinding
import com.example.themoviedbexample.domain.model.Movie
import com.example.themoviedbexample.core.util.Constants.IMG_BASE_URL
import javax.inject.Inject

class MoviesAdapter @Inject constructor(private val onClick: (id: Long) -> Unit) :
    PagingDataAdapter<Movie, MoviesAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(
        private val binding: ItemViewMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movieItem: Movie?,
            onClick: (id: Long) -> Unit
        ) {
            movieItem?.posterPath.let {
                Glide.with(binding.root.context).load(IMG_BASE_URL + it).into(binding.imageMoview)
            }

            val name = movieItem?.title
            binding.textMovie.text = name

            movieItem?.id?.let { id ->
                binding.root.setOnClickListener { onClick.invoke(id) }
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem === newItem
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewMovieBinding.inflate(inflater, null, false)
        return ViewHolder(binding)
    }
}