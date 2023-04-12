package com.example.mipt_android.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mipt_android.data.local.dao.RestaurantDao
import com.example.mipt_android.data.model.local.RestaurantEntity

@Database(
    entities = [RestaurantEntity::class],
    version = 1,
)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao

    companion object {
        @Volatile
        private var Instance: RestaurantDatabase? = null

        fun getDatabase(context: Context): RestaurantDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, RestaurantDatabase::class.java, "restaurant_database")
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}