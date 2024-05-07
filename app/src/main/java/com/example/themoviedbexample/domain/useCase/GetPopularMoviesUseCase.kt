package com.example.themoviedbexample.domain.useCase

import com.example.themoviedbexample.data.model.list.MoviesResult
import com.example.themoviedbexample.domain.repository.MovieRepository
import com.example.themoviedbexample.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository: MovieRepository) {

    fun execute(page: Int?): Flow<Resource<MoviesResult>> = flow {
        try {
            emit(Resource.Loading)
            val movieList = repository.getPopularMovies(page)
            emit(Resource.Success(movieList))
        } catch (e: IOError) {
            emit(Resource.Error(errorMessage = "No internet connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(errorMessage = e.localizedMessage ?: "Error"))
        }
    }
}

