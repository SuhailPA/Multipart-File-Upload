package com.example.multipartjetpackcompose

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.multipartjetpackcompose.ui.theme.MultipartJetpackComposeTheme
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultipartJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: ImagePickerViewModel =
                        viewModel(factory = ViewModelProvider.Factory)
                    val imageUiState = viewModel.imageState.collectAsState()
                    ImageSelectionUi(onButtonClick = {
                        viewModel.updateImageUri(it)

                    }, imageStateUi = imageUiState.value, uploadImage = {
                        val filesDir = applicationContext.filesDir
                        val file = File(filesDir, "Image.png")
                        val inputStream =
                            contentResolver.openInputStream(imageUiState.value.imageUri!!)
                        val outPutStream = FileOutputStream(file)
                        inputStream?.copyTo(outPutStream)
                        viewModel.uploadImage(file)

                    })
                }
            }
        }
    }
}

@Composable
fun ImageSelectionUi(
    modifier: Modifier = Modifier,
    onButtonClick: (Uri) -> Unit,
    uploadImage: () -> Unit,
    imageStateUi: ImageStateUi
) {

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                onButtonClick(uri)
            }
        })
    Column(modifier.fillMaxWidth()) {
        Button(onClick = {
            singlePhotoPicker.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }) {
            Text(text = "Click here to open the Gallery")
        }

        AsyncImage(
            model = imageStateUi.imageUri,
            contentDescription = null,
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Crop
        )
        Button(onClick = { uploadImage() }, modifier = Modifier.padding(10.dp)) {
            Text(text = "Upload")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultipartJetpackComposeTheme {
        Greeting("Android")
    }
}