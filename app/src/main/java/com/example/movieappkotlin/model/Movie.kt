package com.example.movieappkotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Movie : Serializable {
    var title: String? = null
    @PrimaryKey
    var id = 0
    var overview: String? = null
    var poster_path: String? = null
    var backdrop_path: String? = null
}
