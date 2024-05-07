package com.example.themoviedbexample.ui.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbexample.data.model.list.MovieItem
import com.example.themoviedbexample.domain.useCase.GetPopularMoviesUseCase
import com.example.themoviedbexample.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val movieList: MutableList<MovieItem> = mutableListOf()
    private val _movies: MutableLiveData<List<MovieItem>> = MutableLiveData()
    val movies: LiveData<List<MovieItem>> = _movies

    private var page = 1

    init {
        fetchData()
    }


    private fun fetchData() {

        viewModelScope.launch {
            getPopularMoviesUseCase.execute(page).collect {
                when (it) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        it.data?.let { response ->
                            movieList.addAll(response.results ?: emptyList())
                            _movies.value = movieList.toMutableList()
                        }
                    }
                }
            }
        }
    }
}