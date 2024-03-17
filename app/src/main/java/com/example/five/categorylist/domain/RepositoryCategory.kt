package com.example.five.categorylist.domain

import com.example.five.data.models.ArtworkTypesResponse

interface RepositoryCategory {
    suspend fun getArtworkTypes(): ArtworkTypesResponse
}