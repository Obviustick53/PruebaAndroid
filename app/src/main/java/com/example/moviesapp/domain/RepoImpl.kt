package com.example.moviesapp.domain

import com.example.moviesapp.data.DataSource
import com.example.moviesapp.data.model.MovieEntity
import com.example.moviesapp.data.model.Movies
import com.example.moviesapp.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo {

    override suspend fun getMoviesList(): Resource<List<Movies>> {
        return dataSource.getMovies()
    }

    override suspend fun getFavoritesMovies(): Resource<List<MovieEntity>> {
        return dataSource.getMoviesFavorites()
    }

    override suspend fun insertMovie(movie: MovieEntity) {
        dataSource.insertMovieRoom(movie)
    }
}
