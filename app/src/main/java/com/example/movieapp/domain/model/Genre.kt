package com.example.movieapp.domain.model

import com.example.movieapp.data.database.entity.GenreEntity

data class Genre (
    val id: Long? = null,
    val name: String? = null,
    )

fun GenresItem.asMineGenres(): Genre{
    return Genre(id = id, name = name)
}
fun GenreEntity.asMineGenres(): Genre{
    return Genre(id = id, name = name)
}