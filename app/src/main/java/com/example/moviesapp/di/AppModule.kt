package com.example.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.example.moviesapp.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "movietable"
        ).build()

    @Singleton
    @Provides
    fun provideMovieDao(db:AppDataBase) = db.movieDao()
}

