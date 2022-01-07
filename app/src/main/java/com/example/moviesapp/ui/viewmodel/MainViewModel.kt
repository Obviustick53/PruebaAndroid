package com.example.moviesapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.moviesapp.data.model.MovieEntity
import com.example.moviesapp.domain.Repo
import com.example.moviesapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo:Repo):ViewModel(){

   /* private val movieData = MutableLiveData<String>()

    fun setMovie(movieName:String){
        movieData.value = movieName
    }*/

    val fetchMoviesList = liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                emit(repo.getMoviesList())
            } catch (e:Exception){
                emit(Resource.Failure(e))
            }
        }

    fun saveMovie(movie:MovieEntity){
        viewModelScope.launch {
            repo.insertMovie(movie)
        }
    }

    fun getMoviesFavorites() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try{
            emit(repo.getFavoritesMovies())
        } catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    /*val fetchMoviesList =  movieData.distinctUntilChanged().switchMap{
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try{
                emit(repo.getMoviesList())
            } catch (e:Exception){
                emit(Resource.Failure(e))
            }
        }
    }*/
}