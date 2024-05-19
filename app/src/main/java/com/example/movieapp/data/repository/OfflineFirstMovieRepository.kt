package com.example.movieapp.data.repository

import com.example.movieapp.data.database.MovieDataBase
import com.example.movieapp.domain.model.Genre
import com.example.movieapp.domain.model.MinMovie
import com.example.movieapp.domain.model.asMineGenres
import com.example.movieapp.domain.model.asMineMovie
import com.example.movieapp.domain.repository.MovieRepo
import javax.inject.Inject

class OfflineFirstMovieRepository @Inject constructor(private val dataBase: MovieDataBase) :
    MovieRepo {
    override suspend fun getMovieGenres(): List<Genre> {
        return dataBase.getGenresDao().getAll().map {
            it.asMineGenres()
        }
    }

    override suspend fun getMoviesByGenresID(id: String): List<MinMovie> {
        return dataBase.getMovieDao().getMoviesGenreID(id).map {
            it.asMineMovie()
        }
    }

}