package com.example.themoviedbexample.presentation

interface PagingUseCase <In, Out>{
    suspend fun execute(input: In): Out
}