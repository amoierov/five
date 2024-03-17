package com.example.five.savedartworklist.domain

import com.example.five.data.local.SavedArtworkEntity
import javax.inject.Inject

class DeleteSavedArtworkUseCase @Inject constructor (private val savedArtworkRepository: RepositorySavedArtwork) {
    suspend fun deleteSavedArtwork(savedArtworkEntity: SavedArtworkEntity) {
        return savedArtworkRepository.deleteSavedArtwork(savedArtworkEntity)
    }
}