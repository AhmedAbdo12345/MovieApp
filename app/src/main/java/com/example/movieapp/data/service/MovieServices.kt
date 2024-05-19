package com.example.movieapp.data.service

import com.example.movieapp.data.api.ApiConstants
import com.example.movieapp.domain.model.MovieGenresResponse
import com.example.movieapp.domain.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
const val API_KEY = "c50f5aa4e7c95a2a553d29b81aad6dd0"
interface MovieServices {
    @GET(ApiConstants.API_PATH_GENRE_MOVIE_LIST)
    suspend fun getMovieGenres(@Query("api_key")apiKey:String = API_KEY):MovieGenresResponse

    @GET(ApiConstants.API_PATH_DISCOVER_MOVIE)
    suspend fun getDiscoverMovies(@Query("api_key")apiKey:String = API_KEY, @Query("with_genres") genresID:String): MovieResponse

}