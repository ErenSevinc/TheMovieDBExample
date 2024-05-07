package com.example.themoviedbexample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.themoviedbexample.data.dataSource.MovieDataSource
import com.example.themoviedbexample.data.model.detail.MovieDetailResult
import com.example.themoviedbexample.data.model.list.MovieItem
import com.example.themoviedbexample.data.model.list.MoviesResult
import com.example.themoviedbexample.data.network.MovieAPI
import com.example.themoviedbexample.data.repository.paging.MoviePagingSource
import com.example.themoviedbexample.domain.model.Movie
import com.example.themoviedbexample.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val dataSource: MovieDataSource
) : MovieRepository {
    override suspend fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(dataSource)
            }
        ).flow
    }

    /*
    override suspend fun getPopularMovies(page: Int): MoviesResult<List<MovieItem>> {
        return dataSource.getPopularMovies(page = page)
    }

     */
    override suspend fun getMovieDetail(movieId: Long): MovieDetailResult {
        return dataSource.getMovieDetail(movieId = movieId)
    }


}