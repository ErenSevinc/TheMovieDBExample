package com.example.themoviedbexample.data.dataSource

import com.example.themoviedbexample.data.model.detail.MovieDetailResult
import com.example.themoviedbexample.data.model.list.MovieItem
import com.example.themoviedbexample.data.model.list.MoviesResult

interface MovieDataSource {
    suspend fun getPopularMovies(page: Int): MoviesResult<List<MovieItem>>
    suspend fun getMovieDetail(movieId: Long): MovieDetailResult
}