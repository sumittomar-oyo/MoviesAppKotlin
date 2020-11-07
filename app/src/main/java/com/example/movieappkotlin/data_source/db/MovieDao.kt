package com.example.movieappkotlin.data_source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MovieDao {
    @get:Query("SELECT * FROM MovieEntity")
    val all: List<MovieEntity>

    @Insert
    fun insert(movie: MovieEntity?)

    @Query("DELETE FROM MovieEntity")
    fun delete()
}
