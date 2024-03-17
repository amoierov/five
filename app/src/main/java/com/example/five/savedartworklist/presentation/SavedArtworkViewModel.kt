package com.example.five.savedartworklist.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.five.data.local.SavedArtworkEntity
import com.example.five.savedartworklist.domain.AddSavedArtworkUseCase
import com.example.five.savedartworklist.domain.DeleteSavedArtworkUseCase
import com.example.five.savedartworklist.domain.GetSavedArtworksUseCase
import com.example.five.savedartworklist.domain.SearchSavedArtworksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedArtworkViewModel @Inject constructor(
    private val getSavedArtworksUseCase: GetSavedArtworksUseCase,
    private val addSavedArtworkUseCase: AddSavedArtworkUseCase,
    private val deleteSavedArtworkUseCase: DeleteSavedArtworkUseCase,
    private val searchSavedArtworksUseCase: SearchSavedArtworksUseCase
) : ViewModel() {


    private val _favoriteArtworks: MutableLiveData<List<SavedArtworkEntity>> = MutableLiveData()
    val favoriteArtworks: LiveData<List<SavedArtworkEntity>> = _favoriteArtworks


    private var originalSavedArtworks: List<SavedArtworkEntity>? = favoriteArtworks.value

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    init {
        loadFavoriteArtworks()
    }

    private fun loadFavoriteArtworks() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                getSavedArtworksUseCase.getAllSavedArtworks().collect { artworks ->
                    _favoriteArtworks.value = artworks
                    originalSavedArtworks = artworks
                    _isLoading.value = false
                }

            } catch (e: Exception) {
                Log.e(
                    "SavedArtworkList", "ROOM: Ошибка при загрузке избранных артворков ${e.message}"
                )
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleSavedArtwork(savedArtworkEntity: SavedArtworkEntity) {
        viewModelScope.launch {
            try {
                if (savedArtworkEntity.isBookmarked) {
                    deleteSavedArtworkUseCase.deleteSavedArtwork(savedArtworkEntity)
                } else {
                    savedArtworkEntity.isBookmarked = true
                    addSavedArtworkUseCase.insertSavedArtwork(savedArtworkEntity) // Bookmarked
                }
            } catch (e: Exception) {
                Log.e(
                    "SavedArtworkList", "ROOM: Ошибка при изменении статуса артворка ${e.message}"
                )
            }
        }
    }

    fun filterArtworks(searchText: String) {
        _favoriteArtworks.value =
            searchSavedArtworksUseCase.filterArtworks(originalSavedArtworks.orEmpty(), searchText)
    }

    fun restoreOriginalArtworks() {
        _favoriteArtworks.value = originalSavedArtworks.orEmpty()
    }
}