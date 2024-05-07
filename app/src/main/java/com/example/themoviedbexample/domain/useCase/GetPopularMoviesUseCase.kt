package com.example.themoviedbexample.domain.useCase

import androidx.paging.PagingData
import com.example.themoviedbexample.domain.model.Movie
import com.example.themoviedbexample.domain.repository.MovieRepository
import com.example.themoviedbexample.presentation.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : PagingUseCase<Unit, Flow<PagingData<Movie>>> {

    override suspend fun execute(input: Unit): Flow<PagingData<Movie>> {
        return repository.getPopularMovies()
    }
}

