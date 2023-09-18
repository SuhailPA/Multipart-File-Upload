package com.example.multipartjetpackcompose

import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.IOException
import retrofit2.HttpException
import java.io.File

interface ImageUploadRepoImpl {
    suspend fun uploadImage(file: File): Boolean
}

class ImageUploadRepo(val uploadAPI: UploadAPI) : ImageUploadRepoImpl {

    override suspend fun uploadImage(file: File): Boolean {
        return try {
            val body = uploadAPI.uploadImage(
                image = MultipartBody.Part.createFormData(
                    name = "file",
                    filename = file.name,
                    body = file.asRequestBody()
                )
            )
            body.code()
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } catch (e: HttpException) {
            e.printStackTrace()
            false
        }

    }


}