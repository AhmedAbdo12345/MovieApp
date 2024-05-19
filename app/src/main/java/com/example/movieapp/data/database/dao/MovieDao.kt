package com.example.movieapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.database.entity.MovieEntity
import com.example.movieapp.data.database.entity.MovieGenreRef

@Dao
interface MovieDao {
    @Query("SELECT Movie.* FROM Movie,MovieGenreRef  WHERE MovieGenreRef.genreID = :genresID AND MovieGenreRef.movieID = Movie.id")
    suspend fun getMoviesGenreID(genresID: String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherDBModel: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRef(movieGenreRef: MovieGenreRef)

    @Query("DELETE FROM Movie")
    suspend fun deleteMovies()

    @Query("DELETE FROM MovieGenreRef")
    suspend fun deleteMovieGenreRef()
}

