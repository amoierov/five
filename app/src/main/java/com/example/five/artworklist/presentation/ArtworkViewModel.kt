package com.example.five.artworklist.presentation

import android.util.Log
import androidx.lifecycle.LiveData
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

    private val _artworks = MutableLiveData<List<Artwork>>()
    val artworks: MutableLiveData<List<Artwork>> = _artworks

    private val _artworksSaved = MutableLiveData<List<Artwork>?>()
    val artworksSaved: MutableLiveData<List<Artwork>?> = _artworksSaved

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
                withContext(Dispatchers.Main) {
                    originalArtworks = response.data
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

    fun filterArtworks(searchText: String) {
        _artworks.value = searchArtworksUseCase.filterArtworks(originalArtworks.orEmpty(), searchText)
    }

    fun restoreOriginalArtworks() {
        _artworks.value = originalArtworks.orEmpty()
    }
}