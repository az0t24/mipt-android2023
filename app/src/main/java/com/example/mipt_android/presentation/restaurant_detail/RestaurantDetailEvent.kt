package com.example.mipt_android.presentation.restaurant_detail

sealed class RestaurantDetailEvent() {
    data class getRestaurant(val id: Int) : RestaurantDetailEvent()
}