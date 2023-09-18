package com.example.multipartjetpackcompose

import android.text.Editable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object ViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as ImageApplication
            ImagePickerViewModel(application.container.repository)
        }
    }
}