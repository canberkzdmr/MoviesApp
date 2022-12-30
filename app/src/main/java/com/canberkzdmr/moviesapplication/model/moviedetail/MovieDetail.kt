package com.canberkzdmr.moviesapplication.model.moviedetail

data class MovieDetail(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val belongs_to_collection: BelongsToCollection? = null,
    val budget: Int = -1,
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = -1,
    val imdb_id: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val release_date: String = "",
    val revenue: Int = -1,
    val runtime: Int = -1,
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = -1
)