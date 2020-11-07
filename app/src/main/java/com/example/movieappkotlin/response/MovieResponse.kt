package com.example.movieappkotlin.response

import com.example.movieappkotlin.model.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null

    @SerializedName("results")
    @Expose
    var movies: List<Movie>? = null
}