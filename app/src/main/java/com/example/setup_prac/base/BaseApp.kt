package com.example.setup_prac.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application(){

    companion object{
        lateinit var INSTANCE : BaseApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

}