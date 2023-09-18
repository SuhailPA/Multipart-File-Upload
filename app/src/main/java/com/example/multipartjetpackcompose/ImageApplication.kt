package com.example.multipartjetpackcompose

import android.app.Application

class ImageApplication : Application() {

    lateinit var container: ImageUploadContainer
    override fun onCreate() {
        super.onCreate()
        container = ImageContainer(this)
    }
}