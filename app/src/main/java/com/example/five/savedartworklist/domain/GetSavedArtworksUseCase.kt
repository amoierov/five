package com.example.five.savedartworklist.domain

import com.example.five.data.local.SavedArtworkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedArtworksUseCase @Inject constructor (private val savedArtworkRepository: RepositorySavedArtwork) {
    fun getAllSavedArtworks(): Flow<List<SavedArtworkEntity>> {
        return savedArtworkRepository.getAllSavedArtworks()
    }
}