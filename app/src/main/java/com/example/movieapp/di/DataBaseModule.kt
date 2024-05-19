package com.example.movieapp.di


import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.database.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideMovieRepo(@ApplicationContext context: Context) =
            Room.databaseBuilder(context, MovieDataBase::class.java, "Movie_Database")
                .build()

}