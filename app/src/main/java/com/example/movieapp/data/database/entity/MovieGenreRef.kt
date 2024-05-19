package com.example.movieapp.data.database.entity

import androidx.room.Entity

@Entity(tableName = "MovieGenreRef", primaryKeys = ["genreID", "movieID"])
data class MovieGenreRef(val genreID: Long, val movieID: Long)
