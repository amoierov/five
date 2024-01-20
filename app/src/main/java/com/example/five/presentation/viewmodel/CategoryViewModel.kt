package com.example.five.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.five.data.network.NetworkService
import com.example.five.data.models.ArtworkType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<ArtworkType>?>()
    val categories: MutableLiveData<List<ArtworkType>?> = _categories

    // Дополнительное поле для хранения оригинального списка
    private var originalCategories: List<ArtworkType>? = null

    fun loadCategories() {
        viewModelScope.launch {
            try {
                val response = NetworkService.retrofit.getArtworkTypes()
                Log.d("Types", "Response: ${response}")
                withContext(Dispatchers.Main) {
                    originalCategories = response.data
                    _categories.value = originalCategories
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
        val filteredList = _categories.value?.filter {
            it.title.contains(searchText, ignoreCase = true)
        }
        _categories.value = filteredList
    }

    fun restoreOriginalCategories() {
        _categories.value = originalCategories
    }
}