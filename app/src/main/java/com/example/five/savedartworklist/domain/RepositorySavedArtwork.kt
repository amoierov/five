package com.example.five.savedartworklist.domain

import com.example.five.data.local.SavedArtworkEntity
import kotlinx.coroutines.flow.Flow

interface RepositorySavedArtwork {
    fun getAllSavedArtworks(): Flow<List<SavedArtworkEntity>>

    suspend fun insertSavedArtwork(savedArtworkEntity: SavedArtworkEntity)

    suspend fun deleteSavedArtwork(savedArtworkEntity: SavedArtworkEntity)
}