package com.example.mipt_android.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mipt_android.data.model.local.RestaurantEntity

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    fun getAll(): List<RestaurantEntity>

    @Query("SELECT * FROM restaurants WHERE type = 'nearest'")
    fun getNearest(): List<RestaurantEntity>

    @Query("SELECT * FROM restaurants WHERE type = 'popular'")
    fun getPopular(): List<RestaurantEntity>

    @Insert
    fun insertAll(vararg restaurants: RestaurantEntity)

    @Delete
    fun delete(restaurantEntity: RestaurantEntity)
}