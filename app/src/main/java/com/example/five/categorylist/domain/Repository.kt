package com.example.five.categorylist.domain

import com.example.five.data.models.ArtworkTypesResponse

interface Repository {
    suspend fun getArtworkTypes(): ArtworkTypesResponse
}