package com.example.themoviedbexample.data.model.mapper

import com.example.themoviedbexample.data.model.list.MovieItem
import com.example.themoviedbexample.domain.model.Movie

fun MovieItem.toMovie(): Movie {
    return Movie(
        id = this.id ?: -1,
        posterPath = this.posterPath.orEmpty(),
        adult = this.adult ?: false,
        overview = this.overview.orEmpty(),
        releaseDate = this.releaseDate.orEmpty(),
        genreIds = this.genreIds ?: emptyList<Long>().toMutableList(),
        originalTitle = this.originalTitle.orEmpty(),
        originalLanguage = this.originalLanguage.orEmpty(),
        title = this.title.orEmpty(),
        backdropPath = this.backdropPath.orEmpty(),
        popularity = this.popularity ?: -1.0,
        voteCount = this.voteCount ?: -1,
        video = this.video ?: false,
        voteAverage = this.voteAverage ?: -1.0
    )
}

fun List<MovieItem>.toMovieList(): List<Movie> {
    return this.map {
        it.toMovie()
    }
}

fun Movie.toMovieItem() = MovieItem(
    id = this.id,
    adult = this.adult,
    backdropPath = this.backdropPath,
    genreIds = this.genreIds,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun List<Movie>.toMovieItemList(): List<MovieItem> {
    return this.map {
        it.toMovieItem()
    }
}