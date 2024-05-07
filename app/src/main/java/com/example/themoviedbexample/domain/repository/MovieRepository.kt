package com.example.themoviedbexample.domain.repository

import androidx.paging.PagingData
import com.example.themoviedbexample.data.model.detail.MovieDetailResult
import com.example.themoviedbexample.data.model.list.MovieItem
import com.example.themoviedbexample.data.model.list.MoviesResult
import com.example.themoviedbexample.domain.model.Movie
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    suspend fun getPopularMovies(): Flow<PagingData<Movie>>
    suspend fun getMovieDetail(movieId: Long): MovieDetailResult
}

