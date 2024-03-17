package com.example.five.savedartworklist.domain

import com.example.five.data.local.SavedArtworkEntity
import javax.inject.Inject

class AddSavedArtworkUseCase @Inject constructor (private val savedArtworkRepository: RepositorySavedArtwork) {
    suspend fun insertSavedArtwork(savedArtworkEntity: SavedArtworkEntity) {
        return savedArtworkRepository.insertSavedArtwork(savedArtworkEntity)
    }
}