package com.example.themoviedbexample.core.util

sealed class Resource<out T> {
    data object Loading: Resource<Nothing>()
    data class Error(val errorMessage: String?): Resource<Nothing>()
    data class Success<out T>(val data: T?): Resource<T>()
}