package com.example.themoviedbexample.domain.useCase

import com.example.themoviedbexample.data.model.detail.MovieDetailResult
import com.example.themoviedbexample.domain.repository.MovieRepository
import com.example.themoviedbexample.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepository) {

    fun execute(movieId: Long): Flow<Resource<MovieDetailResult>> = flow {
        try {
            emit(Resource.Loading)
            val movieDetail = repository.getMovieDetail(movieId)
            emit(Resource.Success(movieDetail))
        } catch (e: IOError) {
            emit(Resource.Error(errorMessage = "No internet connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(errorMessage = e.localizedMessage ?: "Error"))
        }
    }
}