package com.example.themoviedbexample.presentation.ui.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.themoviedbexample.R
import com.example.themoviedbexample.databinding.FragmentMovieDetailBinding
import com.example.themoviedbexample.databinding.FragmentMovieListBinding
import com.example.themoviedbexample.core.util.Constants.IMG_BASE_URL
import com.example.themoviedbexample.core.util.toDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailBinding
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)

        viewModel.getMovieDetail(args.movieId)

        setupViewObservers()

        return binding.root
    }

    private fun setupViewObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            with(binding) {
                scroll.isVisible != it
                dataLayout.isVisible = !it
                loaderErrorLayout.isVisible = it
                loading.isVisible = it
                tvError.isVisible = !it
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {message ->
            with(binding) {
                if(!message.isNullOrEmpty()) {
                    scroll.isVisible =false
                    dataLayout.isVisible = false
                    loaderErrorLayout.isVisible = true
                    loading.isVisible = false
                    tvError.text = message
                    tvError.isVisible = true
                }
            }
        }

        viewModel.movieDetail.observe(viewLifecycleOwner) { movie ->
            with(binding) {
                loaderErrorLayout.isVisible = false
                loading.isVisible = false
                tvError.isVisible = false

                Glide.with(requireContext()).load(IMG_BASE_URL + movie.posterPath).into(imageMovie)
                textTitle.text =  movie.title ?: movie.originalTitle
                textGenre.text = buildString { movie.genres?.forEach { append("${it.name} ") } }
                textReleaseDate.text = movie.releaseDate?.toDate()
                textDescription.text = movie.overview

                dataLayout.isVisible = true
                scroll.isVisible = true
            }
        }
    }
}