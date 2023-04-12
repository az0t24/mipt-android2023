package com.example.mipt_android.data.model.extensions

import com.example.mipt_android.data.model.local.RestaurantEntity
import com.example.mipt_android.data.model.remote.RemoteRestaurant

fun RemoteRestaurant.toRestaurantEntity(type: String): RestaurantEntity =
    RestaurantEntity(
        id = id,
        name = name,
        logo = image,
        time = deliveryTime,
        type = type
    )

fun RestaurantEntity.toRemoteRestaurant(): RemoteRestaurant =
    RemoteRestaurant(
        id = id,
        name = name,
        image = logo,
        deliveryTime = time
    )