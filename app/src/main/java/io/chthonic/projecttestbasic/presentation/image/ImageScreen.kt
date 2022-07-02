package io.chthonic.projecttestbasic.presentation.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import io.chthonic.projecttestbasic.presentation.ktx.collectAsStateLifecycleAware

@Composable
fun ImageScreen(viewModel: ImageViewModel = hiltViewModel()) {
    ImageScreenImage(
        viewModel.imageUrlToShow.collectAsStateLifecycleAware(
            initial = viewModel.imageUrlToShow.value
        ).value
    )
}

@Composable
fun ImageScreenImage(url: String? = "") {
    AsyncImage(
        model = url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
    )
}