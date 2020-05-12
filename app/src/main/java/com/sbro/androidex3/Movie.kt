package com.sbro.androidex3

data class Movie(
    var popularity: Double,
    var voteCount: Int,
    var video: Boolean,
    var posterPath: String,
    var id: Int,
    var adult: Boolean,
    var backdropPath: String,
    var originalLanguage: String,
    var originalTitle: String,
    var genreIds: List<Int>,
    var title: String,
    var voteAverage: Double,
    var overview: String,
    var releaseDate: String
) {

}