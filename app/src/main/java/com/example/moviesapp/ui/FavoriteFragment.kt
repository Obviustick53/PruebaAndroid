package com.example.moviesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.AppDataBase
import com.example.moviesapp.R
import com.example.moviesapp.data.DataSourceImpl
import com.example.moviesapp.data.model.MovieEntity
import com.example.moviesapp.data.model.Movies
import com.example.moviesapp.domain.RepoImpl
import com.example.moviesapp.ui.viewmodel.MainViewModel
import com.example.moviesapp.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*

@AndroidEntryPoint
class FavoriteFragment : Fragment(),  MainAdapter.OnFavoriteClickListener,MainAdapter.OnMovieClickListener{

    private lateinit var adapter: MainAdapter
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers(){
        viewModel.getMoviesFavorites().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val lista = result.data.map {
                        Movies(it.movieId,it.title,it.release_date,it.vote_average,it.overview,it.poster_path)
                    }.toMutableList()
                    adapter = MainAdapter(requireContext(), lista,this)
                    rv_favs.adapter = adapter
                }
                is Resource.Failure -> {}
            }
        })
    }

    private fun setUpRecyclerView(){
        rv_favs.layoutManager = LinearLayoutManager(requireContext())
        rv_favs.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    override fun onFavoriteClickListener(movie: MovieEntity, position: Int) {
        viewModel.deleteMoviesFavorites(movie)
        rv_favs.adapter?.notifyItemRemoved(position)
        rv_favs.adapter?.notifyItemRangeRemoved(position,rv_favs.adapter?.itemCount!!)
    }

    override fun onMovieClick(movie: Movies) {
        Toast.makeText(requireContext(),"Movie: ${movie.title}",Toast.LENGTH_SHORT).show()
    }

}
