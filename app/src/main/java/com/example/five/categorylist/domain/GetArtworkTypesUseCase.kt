package com.example.five.categorylist.domain

import com.example.five.data.models.ArtworkTypesResponse
import javax.inject.Inject

class GetArtworkTypesUseCase @Inject constructor (private val artworkCategoriesRepositoryCategory: RepositoryCategory) {
    suspend fun getArtworkTypes(): ArtworkTypesResponse {
        return artworkCategoriesRepositoryCategory.getArtworkTypes()
    }
}