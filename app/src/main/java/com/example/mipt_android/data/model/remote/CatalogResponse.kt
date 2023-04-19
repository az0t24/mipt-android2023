package com.example.mipt_android.data.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class CatalogResponse(
    val nearest: List<RemoteRestaurant>,
    val popular: List<RemoteRestaurant>,
    val commercial: RemoteCommercial?
)

@Serializable
data class RemoteRestaurant(
    val id: Int = 0,
    val name: String = "",
    val deliveryTime: String = "",
    val image: String = ""
)

@Serializable
data class RemoteCommercial(
    val picture: String,
    val url: String
)
