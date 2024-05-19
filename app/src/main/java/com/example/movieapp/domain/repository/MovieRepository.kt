package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.MovieGenresResponse
import com.example.movieapp.domain.model.MovieResponse

interface MovieRepository {
    suspend fun getMovieGenres(): MovieGenresResponse
    suspend fun getMoviesDiscover(genresID:String): MovieResponse

}