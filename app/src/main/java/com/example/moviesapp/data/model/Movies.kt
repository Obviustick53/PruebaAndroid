package com.example.moviesapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Movies(
    val id: Int,
    val title: String = "",
    val release_date: String = "",
    val vote_average: Float,
    val overview: String = "",
    val poster_path: String = ""
) : Parcelable

data class MovieList(
    val results:List<Movies>
)

@Entity(tableName = "movietable")
data class MovieEntity(
    @PrimaryKey
    val movieId:Int,
    @ColumnInfo(name = "Titulo")
    val title: String = "",
    @ColumnInfo(name = "Fecha")
    val release_date: String = "",
    @ColumnInfo(name = "Votacion")
    val vote_average: Float,
    @ColumnInfo(name = "Resumen")
    val overview: String,
    @ColumnInfo(name = "Imagen")
    val poster_path: String
)