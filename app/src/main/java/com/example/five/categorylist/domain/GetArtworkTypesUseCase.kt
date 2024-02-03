package com.example.five.categorylist.domain

import com.example.five.data.models.ArtworkTypesResponse
import javax.inject.Inject

class GetArtworkTypesUseCase @Inject constructor (private val artworkCategoriesRepository: Repository) {
    suspend fun getArtworkTypes(): ArtworkTypesResponse {
        return artworkCategoriesRepository.getArtworkTypes()
    }
}