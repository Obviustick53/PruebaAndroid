package com.example.moviesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesapp.data.model.MovieEntity
import com.example.moviesapp.domain.MovieDao

@Database(entities = arrayOf (MovieEntity::class),version = 1)
abstract class AppDataBase:RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object{

        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(context.applicationContext,AppDataBase::class.java,"movietable").build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }

    }
}