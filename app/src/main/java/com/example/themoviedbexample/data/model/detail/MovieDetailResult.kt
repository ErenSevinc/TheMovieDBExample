package com.example.themoviedbexample.data.model.detail

import com.google.gson.annotations.SerializedName

data class MovieDetailResult(
    @SerializedName("adult")
    var adult: Boolean?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("belongs_to_collection")
    var belongsToCollection: BelongsToCollection?,
    @SerializedName("budget")
    var budget: Double?,
    @SerializedName("genres")
    var genres: MutableList<Genres>?,
    @SerializedName("homepage")
    var homepage: String?,
    @SerializedName("id")
    var id: Long?,
    @SerializedName("imdb_id")
    var imdbId: String?,
    @SerializedName("original_language")
    var originalLanguage: String?,
    @SerializedName("original_title")
    var originalTitle: String?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("popularity")
    var popularity: Double?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("production_companies")
    var productionCompanies: MutableList<ProductionCompanies>?,
    @SerializedName("production_countries")
    var productionCountries: MutableList<ProductionCountries>?,
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("revenue")
    var revenue: Double?,
    @SerializedName("runtime")
    var runtime: Long?,
    @SerializedName("spoken_languages")
    var spokenLanguages: MutableList<SpokenLanguages>?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("tagline")
    var tagline: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("video")
    var video: Boolean?,
    @SerializedName("vote_average")
    var voteAverage: Double?,
    @SerializedName("vote_count")
    var voteCount: Long?
)

data class BelongsToCollection(
    @SerializedName("id")
    var id: Long?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("backdrop_path")
    var backdropPath: String?
)

data class Genres(
    @SerializedName("id")
    var id: Long?,
    @SerializedName("name")
    var name: String?
)

data class ProductionCompanies(
    @SerializedName("id")
    var id: Long?,
    @SerializedName("logo_path")
    var logoPath: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("origin_country")
    var originCountry: String?
)

data class ProductionCountries(
    @SerializedName("iso_3166_1")
    var iso31661: String?,
    @SerializedName("name")
    var name: String?
)

data class SpokenLanguages(
    @SerializedName("english_name")
    var englishName: String?,
    @SerializedName("iso_639_1")
    var iso6391: String?,
    @SerializedName("name")
    var name: String?
)