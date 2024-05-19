package com.example.movieapp.data.utils

import androidx.room.TypeConverter
import com.example.movieapp.data.database.entity.GenreEntity
import com.example.movieapp.data.database.entity.MovieEntity
import com.google.gson.Gson

class ConverterDB {
    @TypeConverter
    fun convertMovieDBModelToString(movieEntity: MovieEntity): String {
        return Gson().toJson(movieEntity)
    }

    @TypeConverter
    fun convertStringToMovieDBModel(movieString:String ): MovieEntity {
        return Gson().fromJson(movieString, MovieEntity::class.java)
    }


   @TypeConverter
    fun convertGenresDBModelToString(genreEntity: GenreEntity): String {
        return Gson().toJson(genreEntity)
    }

    @TypeConverter
    fun convertStringToGenresDBModel(genresString:String ): GenreEntity {
        return Gson().fromJson(genresString, GenreEntity::class.java)
    }


}