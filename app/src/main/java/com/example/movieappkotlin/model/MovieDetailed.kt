package com.example.movieappkotlin.model

import java.io.Serializable

class MovieDetailed : Serializable {
    var title: String? = null
    var id = 0
    var overview: String? = null
    var poster_path: String? = null
    var backdrop_path: String? = null
    var genres: List<Genres>? = null
    var release_date: String? = null
    var vote_average = 0.0
}

