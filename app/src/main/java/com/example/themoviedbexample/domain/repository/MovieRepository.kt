package com.example.themoviedbexample.domain.repository

import com.example.themoviedbexample.data.model.detail.MovieDetailResult
import com.example.themoviedbexample.data.model.list.MoviesResult


interface MovieRepository {
    suspend fun getPopularMovies(page: Int? = 1): MoviesResult
    suspend fun getMovieDetail(movieId: Long): MovieDetailResult
}

