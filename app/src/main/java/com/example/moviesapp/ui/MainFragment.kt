package com.example.moviesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.AppDataBase
import com.example.moviesapp.R
import com.example.moviesapp.data.DataSourceImpl
import com.example.moviesapp.data.model.Movies
import com.example.moviesapp.domain.RepoImpl
import com.example.moviesapp.ui.viewmodel.MainViewModel
import com.example.moviesapp.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment(), MainAdapter.OnMovieClickListener {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        //setUpSearchView()
        setUpObservers()
        btn_favs.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
        }

        btn_firebase.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_firebaseFragment)
        }
    }

    private fun setUpObservers(){
        viewModel.fetchMoviesList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    rv_movies.adapter = MainAdapter(requireContext(),result.data,this)
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error al traer los datos ${result.exception}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

   /* private fun setUpSearchView(){
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.setMovie(p0!!)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }*/

    override fun onMovieClick(movie: Movies) {
        val bundle = Bundle()
        bundle.putParcelable("movie",movie)
        findNavController().navigate(R.id.action_mainFragment_to_detailsMovieFragment,bundle)
    }

    private fun setUpRecyclerView(){
        rv_movies.layoutManager = LinearLayoutManager(requireContext())
        rv_movies.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }
}