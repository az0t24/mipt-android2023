package com.example.mipt_android.data.repository

import android.util.Log
import com.example.mipt_android.data.local.dao.RestaurantDao
import com.example.mipt_android.data.model.extensions.toRemoteRestaurant
import com.example.mipt_android.data.model.extensions.toRestaurantEntity
import com.example.mipt_android.data.model.remote.CatalogResponse
import com.example.mipt_android.data.model.remote.RemoteCommercial
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RestaurantRepository
@Inject constructor(
    private val httpClient: HttpClient,
    private val restaurantDao: RestaurantDao
) {

    suspend fun fetchCatalog(): Flow<CatalogResponse> {
        return flow {
            val nearestCache = restaurantDao.getNearest()
            val popularCache = restaurantDao.getPopular()
            if (nearestCache.isNotEmpty() || popularCache.isNotEmpty()) {
                emit(
                    CatalogResponse(
                        nearest = nearestCache.map { it.toRemoteRestaurant() },
                        popular = popularCache.map { it.toRemoteRestaurant() },
                        commercial = null
                    )
                )
            }

            try {
                val response = httpClient.request("http://195.2.84.138:8081/catalog") {
                    method = HttpMethod.Get
                }.body<CatalogResponse>()

                Log.d("fetch", "downloading is done")

                restaurantDao.insertAll(*response.nearest.map {
                    it.toRestaurantEntity("nearest")
                }.toTypedArray())

                restaurantDao.insertAll(*response.nearest.map {
                    it.toRestaurantEntity("popular")
                }.toTypedArray())

                emit(response)
            } catch (e: Exception) {
                Log.e("Fetch", "$e")
            }
        }
    }
}