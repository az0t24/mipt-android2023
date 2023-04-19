package com.example.mipt_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mipt_android.presentation.login.LoginScreen
import com.example.mipt_android.presentation.restaurant_detail.RestaurantDetailScreen
import com.example.mipt_android.presentation.restaurant_detail.RestaurantDetailViewModel
import com.example.mipt_android.presentation.restaurants.RestaurantScreen
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun FoodNinjaNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = stringResource(FoodNinjaScreen.Login.title),
        modifier = modifier
    ) {
        composable(route = "login_screen") {
            LoginScreen(
                navController = navController,
            )
        }
        composable(route = "restaurant_screen") {
            RestaurantScreen(
                navController = navController,
            )
        }
        composable(route = "restaurant_screen/{restaurant_id}") { backStackEntry ->
            RestaurantDetailScreen(backStackEntry.arguments?.getString("restaurant_id") ?: "1")
        }
    }
}