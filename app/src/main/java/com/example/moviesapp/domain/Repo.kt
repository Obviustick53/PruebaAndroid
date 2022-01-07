package com.example.moviesapp.domain

import com.example.moviesapp.data.model.MovieEntity
import com.example.moviesapp.data.model.Movies
import com.example.moviesapp.vo.Resource

interface Repo {

    suspend fun getMoviesList(): Resource<List<Movies>>
    suspend fun getFavoritesMovies(): Resource<List<MovieEntity>>
    suspend fun insertMovie(movie:MovieEntity)
}