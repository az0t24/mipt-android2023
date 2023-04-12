package com.example.mipt_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mipt_android.presentation.login.LoginScreen
import com.example.mipt_android.presentation.restaurants.RestaurantScreen

@Composable
fun FoodNinjaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = FoodNinjaScreen.Login.name,
        modifier = modifier
    ) {
        composable(route = FoodNinjaScreen.Login.name) {
            LoginScreen(
                onSuccessAuth = { navController.navigate(FoodNinjaScreen.Restaurant.name) }
            )
        }
        composable(route = FoodNinjaScreen.Restaurant.name) {
            RestaurantScreen()
        }
    }
}