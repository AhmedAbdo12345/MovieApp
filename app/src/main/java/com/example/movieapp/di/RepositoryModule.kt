package com.example.movieapp.di

import com.example.movieapp.data.repository.OfflineFirstMovieRepository
import com.example.movieapp.domain.repository.MovieRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsMovieRepository(impl:OfflineFirstMovieRepository):MovieRepo

}