package com.example.five.di

import com.example.five.artworklist.domain.RepositoryArtwork
import com.example.five.categorylist.domain.Repository
import com.example.five.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
    @Binds
    @Singleton
    abstract fun bindRepositoryArtwork(repositoryImpl: RepositoryImpl): RepositoryArtwork

}