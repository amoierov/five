package com.example.five.artworklist.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.five.artworklist.domain.GetArtworksUseCase
import com.example.five.artworklist.domain.SearchArtworksUseCase
import com.example.five.artworklist.domain.models.Artwork
import com.example.five.artworklist.domain.models.toSavedArtworkEntity
import com.example.five.data.local.SavedArtworkEntity
import com.example.five.savedartworklist.domain.AddSavedArtworkUseCase
import com.example.five.savedartworklist.domain.DeleteSavedArtworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtworkViewModel @Inject constructor(
    private val getArtworksUseCase: GetArtworksUseCase,
    private val searchArtworksUseCase: SearchArtworksUseCase,
    private val deleteSavedArtworkUseCase: DeleteSavedArtworkUseCase,
    private val addSavedArtworkUseCase: AddSavedArtworkUseCase
) : ViewModel() {

    private val _artworks = MutableLiveData<List<Artwork>>()
    val artworks: LiveData<List<Artwork>> = _artworks

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Дополнительное поле для хранения оригинального списка
    private var originalArtworks: List<Artwork>? = null
    fun loadArtworks(category: String) {
        _isLoading.value = true
        _artworks.value = emptyList()
        viewModelScope.launch {
            try {
                val response = getArtworksUseCase.searchArtworks(category)
                Log.d("ArtworkList", "Response: ${response}")
                originalArtworks = response.data
                withContext(Dispatchers.Main) {
                    _artworks.value = originalArtworks.orEmpty()
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                // Обработка ошибки сети
                withContext(Dispatchers.Main) {
                    Log.e("ArtworkList", "Ошибка сети: ${e.message}")
                    _isLoading.value = false
                }
            }
        }
    }

    fun toggleSavedArtwork(artwork: Artwork) {
        viewModelScope.launch {
            try {
                if (artwork.isBookmarked) {
                    deleteSavedArtworkUseCase.deleteSavedArtwork(artwork.toSavedArtworkEntity())
                } else {
                    artwork.isBookmarked = true
                    addSavedArtworkUseCase.insertSavedArtwork(artwork.toSavedArtworkEntity())

                    // индекс артворка в списке
                    val index = _artworks.value?.indexOfFirst { it.imageId == artwork.imageId }

                    // Если артворк найден, обновите его в списке
                    index?.let {
                        val updatedArtworks = _artworks.value?.toMutableList()
                        updatedArtworks?.set(index, artwork)
                        _artworks.postValue(updatedArtworks.orEmpty())
                    }
                }
            } catch (e: Exception) {
                Log.e(
                    "SavedArtworkList", "ROOM: Ошибка при изменении статуса артворка ${e.message}"
                )
            }
        }
    }

    fun filterArtworks(searchText: String) {
        _artworks.value = searchArtworksUseCase.filterArtworks(originalArtworks.orEmpty(), searchText)
    }

    fun restoreOriginalArtworks() {
        _artworks.value = originalArtworks.orEmpty()
    }
}