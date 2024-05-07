package com.example.themoviedbexample.data.repository

import com.example.themoviedbexample.data.model.detail.MovieDetailResult
import com.example.themoviedbexample.data.model.list.MoviesResult
import com.example.themoviedbexample.data.network.MovieAPI
import com.example.themoviedbexample.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieApi: MovieAPI) : MovieRepository {

    override suspend fun getPopularMovies(page: Int?): MoviesResult {
        return movieApi.getPopularMovies(page = page)
    }

    override suspend fun getMovieDetail(movieId: Long): MovieDetailResult {
        return movieApi.getMovieDetail(movieId = movieId)
    }


}