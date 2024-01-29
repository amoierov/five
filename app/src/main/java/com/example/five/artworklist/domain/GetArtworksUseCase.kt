package com.example.five.artworklist.domain

import com.example.five.artworklist.domain.models.ArtworksResponse
import javax.inject.Inject

class GetArtworksUseCase @Inject constructor (private val artworkRepositoryArtwork: RepositoryArtwork){
    suspend fun searchArtworks(query: String): ArtworksResponse{
        return artworkRepositoryArtwork.searchArtworks(query)
    }
}