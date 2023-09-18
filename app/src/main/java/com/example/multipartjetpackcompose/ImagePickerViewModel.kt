package com.example.multipartjetpackcompose

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multipartjetpackcompose.ImageStateUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

class ImagePickerViewModel(val repository: ImageUploadRepoImpl) : ViewModel() {

    val imageState = MutableStateFlow(ImageStateUi())

    fun updateImageUri(uri: Uri) {
        imageState.update { currentState ->
            currentState.copy(
                imageUri = uri
            )
        }
    }

    fun uploadImage(file : File) {
        viewModelScope.launch {
            repository.uploadImage(file)
        }

    }
}