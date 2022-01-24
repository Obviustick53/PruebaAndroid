package com.example.moviesapp.domain

import androidx.room.*
import com.example.moviesapp.data.model.MovieEntity


@Dao
interface MovieDao {

    @Query("SELECT * FROM movietable")
    suspend fun getAllFavoritesMovies():List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie:MovieEntity)

    @Delete
    suspend fun deleteFavoriteMovie(movie: MovieEntity)
}