package com.example.themoviedbexample.data.dataSource

import com.example.themoviedbexample.data.model.detail.MovieDetailResult
import com.example.themoviedbexample.data.model.list.MovieItem
import com.example.themoviedbexample.data.model.list.MoviesResult
import com.example.themoviedbexample.data.network.MovieAPI
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val api: MovieAPI
) : MovieDataSource {
    override suspend fun getPopularMovies(page: Int): MoviesResult<List<MovieItem>> {
        return api.getPopularMovies(page = page)
    }
    override suspend fun getMovieDetail(movieId: Long): MovieDetailResult {
        return api.getMovieDetail(movieId = movieId)
    }

}