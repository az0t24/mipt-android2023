package com.example.mipt_android.presentation.restaurants

import com.example.mipt_android.data.model.remote.RemoteRestaurant


data class RestaurantState(
    val nearestRestaurants: List<RemoteRestaurant> = emptyList(),
    val popularRestaurants: List<RemoteRestaurant> = emptyList(),
)