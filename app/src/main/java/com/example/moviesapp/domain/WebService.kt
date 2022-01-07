package com.example.moviesapp.domain

import com.example.moviesapp.data.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("movie/popular?api_key=2f111a9ec6beacc93e6e1119cd9cae22")
    suspend fun getMovies(): MovieList
}