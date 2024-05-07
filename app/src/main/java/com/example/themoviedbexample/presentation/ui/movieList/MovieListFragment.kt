package com.example.themoviedbexample.presentation.ui.movieList

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.themoviedbexample.databinding.FragmentMovieListBinding
import com.example.themoviedbexample.presentation.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val viewModel: MovieListViewModel by viewModels()
    private var binding : FragmentMovieListBinding? = null
    private var adapter: MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(layoutInflater, container, false)

        setupLayout()
        setupViewObservers()

        return binding?.root ?: View(context)
    }

    private fun setupLayout() {
        adapter = MoviesAdapter { id ->
            val direction = MovieListFragmentDirections.navigateToDetail(id)
            findNavController().navigate(direction)
        }
        binding?.apply {
            listMovie.adapter = adapter
        }
    }

    private fun setupViewObservers() {
        lifecycleScope.launch {
            viewModel.getPopularMovies().collectLatest { movies ->
                adapter?.submitData(movies)
                Log.d("PAGING",movies.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        binding = null
    }
}