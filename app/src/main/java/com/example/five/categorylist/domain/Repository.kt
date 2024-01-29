package com.example.five.categorylist.domain

import com.example.five.categorylist.domain.models.ArtworkTypesResponse

interface Repository {
    suspend fun getArtworkTypes(): ArtworkTypesResponse
}