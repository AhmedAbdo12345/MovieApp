package com.example.movieapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.database.entity.GenreEntity
@Dao
interface GenresDao {
    @Query("SELECT * FROM Genre")
    suspend fun getAll(): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherDBModel: GenreEntity)

    @Query("DELETE FROM Genre")
    suspend fun deleteGenres()
}