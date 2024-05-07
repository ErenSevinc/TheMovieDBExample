package com.example.themoviedbexample.core.util

interface PagingUseCase <In, Out>{
   suspend fun execute(input: In): Out
}