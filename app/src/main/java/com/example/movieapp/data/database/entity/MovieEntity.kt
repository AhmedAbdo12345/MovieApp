package com.example.movieapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.domain.model.MovieResponse

@Entity(tableName = "Movie")
data class MovieEntity(
    @PrimaryKey
    val id: Long = 0L,
    val overview: String? = null,
    val originalTitle: String? = null,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val popularity: Double? = null,
    val voteAverage: Double? = null,
    val voteCount: Long? = null,
)

fun MovieResponse.Results.asMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id ?: 0,
        overview = overview,
        originalTitle = originalTitle,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        popularity = popularity,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}
