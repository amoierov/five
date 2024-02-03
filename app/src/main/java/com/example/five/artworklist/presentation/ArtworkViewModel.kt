package com.example.five.artworklist.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.five.artworklist.domain.GetArtworksUseCase
import com.example.five.artworklist.domain.SearchArtworksUseCase
import com.example.five.artworklist.domain.models.Artwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtworkViewModel @Inject constructor(
    private val getArtworksUseCase: GetArtworksUseCase,
    private val searchArtworksUseCase: SearchArtworksUseCase
) : ViewModel() {

    private val _artworks = MutableLiveData<List<Artwork>?>()
    val artworks: MutableLiveData<List<Artwork>?> = _artworks

    // Дополнительное поле для хранения оригинального списка
    private var originalArtworks: List<Artwork>? = null
    fun loadArtworks(category: String) {
        viewModelScope.launch {
            try {
                val response = getArtworksUseCase.searchArtworks(category)
                Log.d("ArtworkList", "Response: ${response}")
                withContext(Dispatchers.Main) {
                    originalArtworks = response.data
                    _artworks.value = originalArtworks
                }
            } catch (e: Exception) {
                // Обработка ошибки сети
                withContext(Dispatchers.Main) {
                    Log.e("ArtworkList", "Ошибка сети: ${e.message}")
                }
            }
        }
    }

    fun filterArtworks(searchText: String) {
        _artworks.value = searchArtworksUseCase.filterArtworks(_artworks.value.orEmpty(), searchText)
    }

    fun restoreOriginalArtworks() {
        _artworks.value = originalArtworks
    }
}