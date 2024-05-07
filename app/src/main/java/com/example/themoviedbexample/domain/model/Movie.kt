package com.example.themoviedbexample.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Long,
    val posterPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String,
    val genreIds: MutableList<Long>,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String,
    val popularity: Double,
    val voteCount: Long,
    val video: Boolean,
    val voteAverage: Double
)
