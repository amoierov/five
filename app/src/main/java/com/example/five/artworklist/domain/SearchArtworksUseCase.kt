package com.example.five.artworklist.domain

import com.example.five.artworklist.domain.models.Artwork
import javax.inject.Inject

class SearchArtworksUseCase @Inject constructor() {
    fun filterArtworks(originalList: List<Artwork>, searchText: String): List<Artwork> {
        return originalList.filter { artwork ->
            artwork.title.contains(searchText, ignoreCase = true) ||
                    artwork.artistTitle.contains(searchText, ignoreCase = true) ||
                    artwork.dateDisplay.contains(searchText, ignoreCase = true)
        }
    }
}