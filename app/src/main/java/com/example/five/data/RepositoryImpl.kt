package com.example.five.data

import com.example.five.artworklist.domain.RepositoryArtwork
import com.example.five.artworklist.domain.models.ArtworksResponse
import com.example.five.categorylist.domain.Repository
import com.example.five.categorylist.domain.models.ArtworkTypesResponse
import com.example.five.data.network.ApiService
import javax.inject.Inject

class RepositoryImpl @Inject constructor (private val retrofit: ApiService): Repository, RepositoryArtwork {
    override suspend fun getArtworkTypes(): ArtworkTypesResponse {
        return retrofit.getArtworkTypes()
    }
    override suspend fun searchArtworks(query: String): ArtworksResponse {
        return retrofit.searchArtworks(query)
    }
}