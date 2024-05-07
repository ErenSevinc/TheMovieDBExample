package com.example.themoviedbexample.presentation.ui.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedbexample.data.model.detail.MovieDetailResult
import com.example.themoviedbexample.domain.useCase.GetMovieDetailUseCase
import com.example.themoviedbexample.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetailResult>()
    val movieDetail: LiveData<MovieDetailResult> = _movieDetail

    fun getMovieDetail(movieId: Long) {
        viewModelScope.launch {
            getMovieDetailUseCase.execute(movieId).collect {
                when(it) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        it.data?.let { result ->
                            _movieDetail.value = result
                        }
                    }
                }
            }
        }
    }
}