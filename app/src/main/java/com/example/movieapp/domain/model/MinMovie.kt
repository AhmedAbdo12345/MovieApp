package com.example.movieapp.domain.model

import com.example.movieapp.data.database.entity.MovieEntity
import java.io.Serializable

data class MinMovie(
    val id: Long? = null,
    val overview: String? = null,
    val title: String? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val popularity: Double? = null,
    val voteAverage: Double? = null,
    val voteCount: Long? = null,
) : Serializable

fun MovieEntity.asMineMovie(): MinMovie {
    return MinMovie(
        id = id,
        overview = overview,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        popularity = popularity,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun MovieResponse.Results.asMineMovie(): MinMovie {
    return MinMovie(
        id = id,
        overview = overview,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        popularity = popularity,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}
