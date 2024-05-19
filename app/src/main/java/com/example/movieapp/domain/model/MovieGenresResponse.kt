package com.example.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieGenresResponse(
    @SerializedName("genres") val genres: List<GenresItem>? = null,
)
data class GenresItem(
    @SerializedName("name") val name: String? = null,
    @SerializedName("id") val id: Long? = null,
)
