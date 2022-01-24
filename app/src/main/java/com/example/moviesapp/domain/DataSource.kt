package com.example.moviesapp.domain

import com.example.moviesapp.data.model.MovieEntity
import com.example.moviesapp.data.model.Movies
import com.example.moviesapp.vo.Resource

interface DataSource {

    suspend fun getMovies(): Resource<List<Movies>>

    suspend fun insertMovieRoom(movie: MovieEntity)

    suspend fun getMoviesFavorites(): Resource<List<MovieEntity>>

    suspend fun deleteMovieRoom(movie: MovieEntity)
}