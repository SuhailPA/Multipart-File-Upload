package com.example.multipartjetpackcompose

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadAPI {

    @Multipart
    @POST("1695025012187-6044599025044")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ):Response<ResponseBody>
}