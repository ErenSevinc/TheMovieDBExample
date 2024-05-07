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

    private val _isLoading =MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error =MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _movieDetail = MutableLiveData<MovieDetailResult>()
    val movieDetail: LiveData<MovieDetailResult> = _movieDetail

    fun getMovieDetail(movieId: Long) {
        viewModelScope.launch {
            getMovieDetailUseCase.execute(movieId).collect {
                when(it) {
                    is Resource.Error -> {
                        it.errorMessage?.let { result ->
                            _isLoading.value = false
                            _error.value = result
                        }
                    }
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }
                    is Resource.Success -> {
                        it.data?.let { result ->
                            _isLoading.value = false
                            _movieDetail.value = result
                        }
                    }
                }
            }
        }
    }
}