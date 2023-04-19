package com.example.mipt_android.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mipt_android.R

sealed class FoodNinjaScreen(@StringRes val title: Int) {
    object Login : FoodNinjaScreen(title = R.string.login_screen)
    object Restaurant : FoodNinjaScreen(title = R.string.restaurant_screen)
    data class RestaurantDetail(val id: Int) : FoodNinjaScreen(title = R.string.restaurant_screen) {
        @Composable
        fun getRoute() = stringResource(title) + "/" + id.toString()
    }
}