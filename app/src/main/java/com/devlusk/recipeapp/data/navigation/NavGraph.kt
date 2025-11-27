package com.devlusk.recipeapp.data.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devlusk.recipeapp.data.model.Category
import com.devlusk.recipeapp.screens.category.CategoryScreen
import com.devlusk.recipeapp.screens.category.CategoryViewModel
import com.devlusk.recipeapp.screens.detail.DetailScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val categoryViewModel: CategoryViewModel = viewModel()
    val viewState by categoryViewModel.categoriesState

    NavHost(
        navController = navController,
        startDestination = Routes.CATEGORY_SCREEN
    ) {
        composable(route = Routes.CATEGORY_SCREEN) {
            CategoryScreen(
                viewState = viewState,
                navigateToDetail = { category ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("cath", category)

                    navController.navigate(Routes.DETAIL_SCREEN)
                }
            )
        }

        composable(route = Routes.DETAIL_SCREEN) {
            val category = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Category>("cath")
                ?: Category("", "", "", "")

            DetailScreen(category)
        }
    }
}