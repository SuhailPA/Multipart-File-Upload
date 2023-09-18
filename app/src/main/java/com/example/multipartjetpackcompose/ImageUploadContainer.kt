package com.example.multipartjetpackcompose

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ImageUploadContainer {
    val repository: ImageUploadRepoImpl
}

class ImageContainer(context: Context) : ImageUploadContainer {

    override val repository: ImageUploadRepoImpl by lazy {
        ImageUploadRepo(imageAPI)
    }

    val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl("https://www.toptal.com/developers/postbin/")
        .build()

    val imageAPI = retrofit.create(UploadAPI::class.java)
}