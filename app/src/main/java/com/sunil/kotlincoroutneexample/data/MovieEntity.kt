package com.sunil.kotlincoroutneexample.data

import com.google.gson.annotations.SerializedName


class MovieEntity {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("adult")
    var isAdult: Boolean = false

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("vote_count")
    var voteCount: Int = 0

    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble()

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null
}