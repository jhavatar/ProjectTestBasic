package io.chthonic.projecttestbasic.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import io.chthonic.projecttestbasic.Destination
import io.chthonic.projecttestbasic.R
import io.chthonic.projecttestbasic.presentation.image.ImageViewModel
import io.chthonic.projecttestbasic.presentation.ktx.collectAsStateLifecycleAware
import io.chthonic.projecttestbasic.presentation.ktx.navigateWithObject
import io.chthonic.projecttestbasic.presentation.main.MainViewModel.NavigationTarget.ImageScreen

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel(), navController: NavController) {
    val showProgressState = viewModel.loadingIsVisible.collectAsStateLifecycleAware(
        initial = false,
        scope = viewModel.viewModelScope
    )

    viewModel.navigate.collectAsStateLifecycleAware(
        initial = null,
        scope = viewModel.viewModelScope,
    ).value?.let { navTarget ->
        viewModel.onNavigationObserved()
        when (navTarget) {
            is ImageScreen -> {
                navController.navigateWithObject(
                    Destination.Image.route,
                    arguments = bundleOf(ImageViewModel.IMAGE_URL_KEY to navTarget.url)
                )
            }
        }
    }

    MainContent { viewModel.onImageButtonClicked() }
    if (showProgressState.value) {
        MainProgress()
    }
}

@Composable
fun MainProgress() {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .background(White, shape = RoundedCornerShape(8.dp))
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun MainContent(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Button(
            onClick = onClick,
        ) {
            Text(stringResource(R.string.dog_button))
        }
    }
}