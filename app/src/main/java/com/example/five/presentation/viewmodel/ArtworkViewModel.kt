package com.example.five.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.five.data.network.NetworkService
import com.example.five.data.models.Artwork
import com.example.five.data.models.ArtworkType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArtworkViewModel : ViewModel() {

    private val _artworks = MutableLiveData<List<Artwork>?>()
    val artworks: MutableLiveData<List<Artwork>?> = _artworks

    // Дополнительное поле для хранения оригинального списка
    private var originalArtworks: List<Artwork>? = null
    fun loadArtworks(category: String) {
        viewModelScope.launch {
            try {
                val response = NetworkService.retrofit.searchArtworks(category)
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

    fun filterCategories(searchText: String) {
        val filteredList = _artworks.value?.filter { artwork ->
            artwork.title.contains(searchText, ignoreCase = true) ||
                    artwork.artistTitle.contains(searchText, ignoreCase = true) ||
                    artwork.dateDisplay.contains(searchText, ignoreCase = true)
        }
        _artworks.value = filteredList
    }

    fun restoreOriginalCategories() {
        _artworks.value = originalArtworks
    }
}