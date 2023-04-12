package com.example.mipt_android.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.mipt_android.data.local.dao.RestaurantDao
import com.example.mipt_android.data.local.db.RestaurantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

private const val DB_NAME = "db_name"

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    @Named(value = DB_NAME)
    fun provideDatabaseName(): String {
        return DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(value = DB_NAME) dbname: String,
        @ApplicationContext context: Context
    ): RestaurantDatabase {
        return Room.databaseBuilder(context, RestaurantDatabase::class.java, dbname)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRestaurantDao(appDatabase: RestaurantDatabase): RestaurantDao {
        return appDatabase.restaurantDao()
    }
}