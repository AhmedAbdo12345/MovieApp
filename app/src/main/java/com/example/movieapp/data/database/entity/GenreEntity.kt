package com.example.movieapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.domain.model.GenresItem

@Entity(tableName = "Genre")
data class GenreEntity(
    @PrimaryKey
    val id: Long = 0L,
    val name: String? = null,
)

fun GenresItem.asGenreEntity(): GenreEntity {
    return GenreEntity(id = id ?: 0, name = name)
}
