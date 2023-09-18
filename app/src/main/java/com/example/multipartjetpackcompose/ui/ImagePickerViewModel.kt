package com.example.multipartjetpackcompose.ui

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.multipartjetpackcompose.ImageStateUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ImagePickerViewModel : ViewModel() {

    val imageState = MutableStateFlow(ImageStateUi())

    fun updateImageUri(uri: Uri) {
        imageState.update { currentState ->
            currentState.copy(
                imageUri = uri
            )
        }
    }
}