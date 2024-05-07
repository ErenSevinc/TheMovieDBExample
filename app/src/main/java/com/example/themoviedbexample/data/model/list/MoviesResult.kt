package com.example.themoviedbexample.data.model.list

import com.example.themoviedbexample.domain.model.Movie
import com.google.gson.annotations.SerializedName

class MoviesResult<T : Any?>(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("total_results")
    val totalResults: Long? = null,
    @SerializedName("total_pages")
    val totalPages: Long? = null,
    @SerializedName("results")
    val results: T? = null
)


data class MovieItem(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("genre_ids")
    val genreIds: MutableList<Long>?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("vote_count")
    val voteCount: Long?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?
)