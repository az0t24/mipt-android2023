package com.example.mipt_android.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mipt_android.presentation.navigation.FoodNinjaNavHost
import com.example.mipt_android.presentation.navigation.FoodNinjaScreen

@Composable
fun FoodNinjaMainScreen(
    navController: NavHostController
) {
    Scaffold { innerPadding ->
        FoodNinjaNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
        )
    }
}