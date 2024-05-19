package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Genre
import com.example.movieapp.domain.model.MinMovie

interface MovieRepo {
    suspend fun getMovieGenres(): List<Genre>
    suspend fun getMoviesByGenresID(id:String): List<MinMovie>
}