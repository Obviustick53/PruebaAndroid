package com.example.moviesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.moviesapp.AppDataBase
import com.example.moviesapp.R
import com.example.moviesapp.data.DataSourceImpl
import com.example.moviesapp.data.model.MovieEntity
import com.example.moviesapp.data.model.Movies
import com.example.moviesapp.domain.RepoImpl
import com.example.moviesapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details_movie.*

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()

    private lateinit var movie:Movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            movie = it.getParcelable<Movies>("movie")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500"+movie.poster_path).centerCrop().into(img_movie)
        movie_title.text = movie.title
        movie_date.text = movie.release_date
        movie_vote.text = movie.vote_average.toString()
        movie_overview.text = movie.overview
        btn_favs.setOnClickListener {
            viewModel.saveMovie(MovieEntity(movie.id,movie.title,movie.release_date,movie.vote_average,movie.overview,movie.poster_path))
            Toast.makeText(requireContext(),"Se guardo la pelicula a favoritos", Toast.LENGTH_SHORT).show()
        }
    }
}