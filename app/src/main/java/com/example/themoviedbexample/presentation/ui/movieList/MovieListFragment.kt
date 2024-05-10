package com.example.themoviedbexample.presentation.ui.movieList

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.themoviedbexample.databinding.FragmentMovieListBinding
import com.example.themoviedbexample.presentation.adapter.LoaderAdapter
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
            listMovie.adapter = adapter?.withLoadStateHeaderAndFooter(
                header =LoaderAdapter {
                    adapter?.retry()
                },
                footer = LoaderAdapter {
                    adapter?.retry()
                }
            )
            adapter?.addLoadStateListener {loadState ->
                listMovie.isVisible = loadState.source.refresh is LoadState.NotLoading
                loading.isVisible = loadState.source.refresh is LoadState.Loading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error

                handleError(loadState)
            }

            buttonRetry.setOnClickListener {
                adapter?.retry()
            }
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

    private fun handleError(loadStates: CombinedLoadStates) {
        val errorState = loadStates.source.append as? LoadState.Error
            ?: loadStates.source.prepend as? LoadState.Error


        errorState?.let {
            Toast.makeText(requireContext(),errorState.error.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        binding = null
    }
}