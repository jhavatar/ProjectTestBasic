package io.chthonic.projecttestbasic.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.telefonica.tweaks.Tweaks.Companion.TWEAKS_NAVIGATION_ENTRYPOINT
import io.chthonic.projecttestbasic.R
import io.chthonic.projecttestbasic.presentation.Destination
import io.chthonic.projecttestbasic.presentation.ktx.collectAsStateLifecycleAware
import io.chthonic.projecttestbasic.presentation.ktx.navigateWithObject
import io.chthonic.projecttestbasic.presentation.main.MainViewModel.NavigationTarget.ImageScreen
import io.chthonic.projecttestbasic.presentation.main.MainViewModel.NavigationTarget.SettingsScreen

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
                    arguments = bundleOf(
                        Destination.Image.ARGUMENT_KEY to navTarget.url
                    )
                )
            }

            SettingsScreen -> navController.navigate(TWEAKS_NAVIGATION_ENTRYPOINT)
        }
    }

    MainContent(
        onImageButtonClicked = viewModel::onImageButtonClicked,
        onSettingsButtonClicked = viewModel::onSettingsButtonClicked,
    )
    if (showProgressState.value) {
        MainProgress()
    }
}

@Preview
@Composable
private fun MainProgress() {
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

@Preview
@Composable
private fun MainContent(
    onImageButtonClicked: () -> Unit = {},
    onSettingsButtonClicked: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Button(
            onClick = onImageButtonClicked,
        ) {
            Text(stringResource(R.string.dog_button))
        }
        Button(
            onClick = onSettingsButtonClicked,
        ) {
            Text(stringResource(R.string.settings_button))
        }
    }
}