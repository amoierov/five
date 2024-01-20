package com.example.five.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.five.data.network.ApiService
import com.example.five.data.network.NetworkService
import com.example.five.data.models.Artwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtworkViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {

    private val _artworks = MutableLiveData<List<Artwork>>()
    val artworks: LiveData<List<Artwork>> = _artworks
    fun loadArtworks(category: String) {
        viewModelScope.launch {
            try {
                val response = NetworkService.retrofit.searchArtworks(category)
                Log.d("ArtworkList", "Response: ${response}")
                withContext(Dispatchers.Main) {
                    _artworks.value = response.data
                }
            } catch (e: Exception) {
                // Обработка ошибки сети
                withContext(Dispatchers.Main) {
                    Log.e("ArtworkList", "Ошибка сети: ${e.message}")
                }
            }
        }
    }
}