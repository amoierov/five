package com.example.five.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.five.data.network.ApiService
import com.example.five.data.network.NetworkService
import com.example.five.data.models.ArtworkType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class CategoryViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _categories = MutableLiveData<List<ArtworkType>>()
    val categories: LiveData<List<ArtworkType>> = _categories

    fun loadCategories() {
        viewModelScope.launch {
            try {
                val response = NetworkService.retrofit.getArtworkTypes()
                Log.d("Types", "Response: ${response}")
                withContext(Dispatchers.Main) {
                    _categories.value = response.data
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