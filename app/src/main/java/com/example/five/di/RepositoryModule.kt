package com.example.five.di

import com.example.five.artworklist.domain.RepositoryArtwork
import com.example.five.categorylist.domain.RepositoryCategory
import com.example.five.data.repository.RemoteRepositoryImpl
import com.example.five.data.repository.RoomRepositorySavedArtworkImpl
import com.example.five.savedartworklist.domain.RepositorySavedArtwork
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
    abstract fun bindRepository(repositoryImpl: RemoteRepositoryImpl): RepositoryCategory
    @Binds
    @Singleton
    abstract fun bindRepositoryArtwork(repositoryImpl: RemoteRepositoryImpl): RepositoryArtwork

    @Binds
    @Singleton
    abstract fun bindRepositorySavedArtwork(repositoryImpl: RoomRepositorySavedArtworkImpl): RepositorySavedArtwork
}