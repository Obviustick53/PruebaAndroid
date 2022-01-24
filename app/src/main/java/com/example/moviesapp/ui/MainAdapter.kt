package com.example.moviesapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.base.BaseViewHolder
import com.example.moviesapp.data.model.MovieEntity
import com.example.moviesapp.data.model.Movies
import kotlinx.android.synthetic.main.movies_row.view.*

class MainAdapter(private val context: Context, private val moviesList: List<Movies>,
                    private val itemClickListener:OnMovieClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMovieClickListener{
        fun onMovieClick(movie: Movies)
    }
    interface OnFavoriteClickListener{
        fun onFavoriteClickListener(movie:MovieEntity,position: Int)

    }    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.movies_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(moviesList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Movies>(itemView) {
        override fun bind(item: Movies, position: Int) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500"+item.poster_path).centerCrop().into(itemView.img_movie)
            itemView.txt_title.text = item.title
            itemView.txt_release_date.text = item.release_date
            itemView.txt_vote_average.text = item.vote_average.toString()
            itemView.setOnClickListener { itemClickListener.onMovieClick(item) }
        }

    }

}