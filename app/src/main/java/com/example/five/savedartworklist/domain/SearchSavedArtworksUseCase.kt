package com.example.five.savedartworklist.domain

import com.example.five.artworklist.domain.models.Artwork
import com.example.five.data.local.SavedArtworkEntity
import javax.inject.Inject

class SearchSavedArtworksUseCase @Inject constructor() {
    fun filterArtworks(originalList: List<SavedArtworkEntity>, searchText: String): List<SavedArtworkEntity> {
        return originalList.filter { artwork ->
            artwork.title?.contains(searchText, ignoreCase = true) ?: false ||
                    artwork.artistTitle?.contains(searchText, ignoreCase = true) ?: false ||
                    artwork.dateDisplay?.contains(searchText, ignoreCase = true) ?: false
        }
    }
}