package com.devlusk.recipeapp.screens.recipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devlusk.recipeapp.screens.category.CategoryViewModel

@Composable
fun RecipeScreen(navController: NavController) {
    val categoryViewModel: CategoryViewModel = viewModel()
    val viewState by categoryViewModel.categoriesState
}