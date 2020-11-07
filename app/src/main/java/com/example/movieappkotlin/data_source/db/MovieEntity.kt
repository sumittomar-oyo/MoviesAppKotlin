package com.example.movieappkotlin.data_source.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class MovieEntity : Serializable {
    var title: String? = null

    @PrimaryKey
    var id = 0
    var overview: String? = null
    var poster_path: String? = null
    var backdrop_path: String? = null
}
