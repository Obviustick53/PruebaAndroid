package com.example.moviesapp.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesapp.data.model.MovieEntity


@Dao
interface MovieDao {

    @Query("SELECT * FROM movietable")
    suspend fun getAllFavoritesMovies():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie:MovieEntity)
}