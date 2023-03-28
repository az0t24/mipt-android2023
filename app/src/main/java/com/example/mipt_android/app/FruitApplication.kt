package com.example.mipt_android.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BusyAppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}