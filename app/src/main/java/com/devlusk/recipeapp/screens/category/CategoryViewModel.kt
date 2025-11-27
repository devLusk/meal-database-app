package com.devlusk.recipeapp.screens.category

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devlusk.recipeapp.data.model.Category
import com.devlusk.recipeapp.data.remote.apiService
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _categorieState = mutableStateOf(CategoryState())
    val categoriesState: State<CategoryState> = _categorieState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = apiService.getCategories()
                _categorieState.value = _categorieState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _categorieState.value = _categorieState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    data class CategoryState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}