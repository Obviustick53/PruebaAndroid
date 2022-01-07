package com.example.moviesapp.data

import com.example.moviesapp.AppDataBase
import com.example.moviesapp.data.model.MovieEntity
import com.example.moviesapp.data.model.Movies
import com.example.moviesapp.vo.Resource
import com.example.moviesapp.vo.RetrofitClient

class DataSource(private val appDataBase: AppDataBase){

    suspend fun getMovies(): Resource<List<Movies>>{
        return Resource.Success(RetrofitClient.webservice.getMovies().results)
    }

    suspend fun insertMovieRoom(movie:MovieEntity){
        appDataBase.movieDao().insertFavoriteMovie(movie)
    }

    suspend fun getMoviesFavorites(): Resource<List<MovieEntity>>{
        return Resource.Success(appDataBase.movieDao().getAllFavoritesMovies())
    }
    /*val generateMovieList = Resource.Success(listOf(
        Movies("Spiderman","12-12-2021",8.7f,"Pelicula de Marvel","https://es.web.img2.acsta.net/pictures/21/12/01/12/07/0243323.jpg"),
        Movies("Resident Evil","3-12-2021",6.7f,"Pelicula de Zombies","https://pics.filmaffinity.com/Resident_Evil_Bienvenidos_a_Raccoon_City-764554100-mmed.jpg"),
        Movies("Dune","27-11-2021",8.5f,"Pelicula de Accion","https://es.web.img3.acsta.net/pictures/21/08/25/14/35/3349802.jpg"),
        Movies("Encanto","12-11-2021",7.7f,"Pelicula de Disney","https://pics.filmaffinity.com/Encanto-153413687-large.jpg")
        ))*/
}