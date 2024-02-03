package com.example.five.categorylist.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.five.categorylist.domain.GetArtworkTypesUseCase
import com.example.five.categorylist.domain.SearchCategoriesUseCase
import com.example.five.categorylist.domain.models.ArtworkType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getArtworkTypesUseCase: GetArtworkTypesUseCase,
    private val searchCategoriesUseCase: SearchCategoriesUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<List<ArtworkType>?>()
    val categories: MutableLiveData<List<ArtworkType>?> = _categories

    // Дополнительное поле для хранения оригинального списка
    private var originalCategories: List<ArtworkType>? = null

    fun loadCategories() {
        viewModelScope.launch {
            try {
                val response = getArtworkTypesUseCase.getArtworkTypes()
                Log.d("Types", "Response: ${response}")
                withContext(Dispatchers.Main) {
                    originalCategories = response.data
                    _categories.value = originalCategories
                }
            } catch (e: Exception) {
                // Обработка ошибки сети
                withContext(Dispatchers.Main) {
                    Log.e("CategoryList", "Ошибка сети: ${e.message}")
                }
            }
        }
    }

    fun filterCategories(searchText: String) {
        _categories.value = searchCategoriesUseCase.filterCategories(_categories.value.orEmpty(), searchText)
    }

    fun restoreOriginalCategories() {
        _categories.value = originalCategories
    }
}