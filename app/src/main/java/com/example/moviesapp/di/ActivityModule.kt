package com.example.moviesapp.di

import com.example.moviesapp.data.DataSourceImpl
import com.example.moviesapp.domain.DataSource
import com.example.moviesapp.domain.Repo
import com.example.moviesapp.domain.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(repoImpl:RepoImpl):Repo

    @Binds
    abstract fun bindDataSourceImpl(dataSourceImpl:DataSourceImpl):DataSource
}