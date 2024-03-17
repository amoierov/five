package com.example.five.data.repository

import com.example.five.artworklist.domain.RepositoryArtwork
import com.example.five.data.models.ArtworksResponse
import com.example.five.categorylist.domain.RepositoryCategory
import com.example.five.data.models.ArtworkTypesResponse
import com.example.five.data.network.ApiService
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor (private val retrofit: ApiService): RepositoryCategory, RepositoryArtwork {
    override suspend fun getArtworkTypes(): ArtworkTypesResponse {
        return retrofit.getArtworkTypes()
    }
    override suspend fun searchArtworks(query: String): ArtworksResponse {
        return retrofit.searchArtworks(query)
    }
}