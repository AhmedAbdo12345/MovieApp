package com.example.movieapp.data.repository

import com.example.movieapp.data.service.MovieServices
import com.example.movieapp.domain.model.MovieGenresResponse
import com.example.movieapp.domain.model.MovieResponse
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieServices: MovieServices) :
    MovieRepository {
    override suspend fun getMovieGenres(): MovieGenresResponse {
        return movieServices.getMovieGenres()
    }

    override suspend fun getMoviesDiscover(genresID: String): MovieResponse {
        return movieServices.getDiscoverMovies(genresID = genresID)
    }
}