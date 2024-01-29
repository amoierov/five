package com.example.five.categorylist.domain

import com.example.five.categorylist.domain.models.ArtworkType
import javax.inject.Inject

class SearchCategoriesUseCase @Inject constructor() {
    fun filterCategories(originalList: List<ArtworkType>, searchText: String): List<ArtworkType> {
        return originalList.filter { it.title.contains(searchText, ignoreCase = true)}
    }
}