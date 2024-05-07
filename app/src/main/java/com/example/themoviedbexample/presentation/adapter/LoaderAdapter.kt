package com.example.themoviedbexample.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedbexample.databinding.ItemLoaderBinding
import com.example.themoviedbexample.databinding.ItemViewMovieBinding

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {

    class LoaderViewHolder(private val binding: ItemLoaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(loadState: LoadState) {
                binding.loading.isVisible = loadState is LoadState.Loading
            }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLoaderBinding.inflate(inflater, null, false)
        return LoaderAdapter.LoaderViewHolder(binding)
    }
}