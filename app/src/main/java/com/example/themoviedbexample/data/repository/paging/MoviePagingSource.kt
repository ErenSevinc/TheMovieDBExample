package com.example.themoviedbexample.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.themoviedbexample.data.dataSource.MovieDataSource
import com.example.themoviedbexample.data.model.mapper.toMovieList
import com.example.themoviedbexample.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val dataSource: MovieDataSource
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val response = dataSource.getPopularMovies(currentPage)
            LoadResult.Page(
                data = response.results?.toMovieList() ?: emptyList(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.results?.isEmpty() == true) null else (response.page?.plus(1))
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}