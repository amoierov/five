package com.example.five.artworklist.domain

import com.example.five.data.models.ArtworksResponse

interface RepositoryArtwork {
    suspend fun searchArtworks(query: String): ArtworksResponse
}