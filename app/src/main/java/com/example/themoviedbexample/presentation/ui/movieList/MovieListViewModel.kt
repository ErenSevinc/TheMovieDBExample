package com.example.themoviedbexample.presentation.ui.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.themoviedbexample.domain.model.Movie
import com.example.themoviedbexample.domain.useCase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    suspend fun getPopularMovies(): Flow<PagingData<Movie>> {
        return getPopularMoviesUseCase.execute(Unit).cachedIn(viewModelScope)
    }
}