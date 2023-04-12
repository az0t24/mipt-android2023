package com.example.mipt_android.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mipt_android.R

enum class FoodNinjaScreen(@StringRes val title: Int) {
    Login(title = R.string.login_screen),
    Restaurant(title = R.string.restaurant_screen),
}