package com.example.themoviedbexample.data.network

import com.example.themoviedbexample.data.model.detail.MovieDetailResult
import com.example.themoviedbexample.data.model.list.MovieItem
import com.example.themoviedbexample.data.model.list.MoviesResult
import com.example.themoviedbexample.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET(Constants.GET_POPULARS_URL)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int? = 1
    ): MoviesResult<List<MovieItem>>

    @GET("${Constants.GET_DETAIL_URL}/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
    ): MovieDetailResult

}