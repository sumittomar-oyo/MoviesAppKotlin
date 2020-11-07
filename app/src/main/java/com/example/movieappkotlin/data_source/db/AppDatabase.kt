package com.example.movieappkotlin.data_source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieappkotlin.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao?
}