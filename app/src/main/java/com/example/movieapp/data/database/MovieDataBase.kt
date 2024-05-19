package com.example.movieapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.data.database.dao.GenresDao
import com.example.movieapp.data.database.dao.MovieDao
import com.example.movieapp.data.database.entity.GenreEntity
import com.example.movieapp.data.database.entity.MovieEntity
import com.example.movieapp.data.database.entity.MovieGenreRef
import com.example.movieapp.data.utils.ConverterDB

@Database(entities = [MovieGenreRef::class,MovieEntity::class, GenreEntity::class], version = 1)
@TypeConverters(ConverterDB::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getGenresDao(): GenresDao

}