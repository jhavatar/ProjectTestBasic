package io.chthonic.projecttestbasic.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.telefonica.tweaks.addTweakGraph
import io.chthonic.projecttestbasic.presentation.image.ImageScreen
import io.chthonic.projecttestbasic.presentation.main.MainScreen

@Composable
fun AppContainerNavHost(
    appContainerState: AppContainerState,
    padding: PaddingValues
) = NavHost(
    navController = appContainerState.navController,
    startDestination = Destination.Main.route,
    modifier = androidx.compose.ui.Modifier.padding(padding)
) {
    composable(Destination.Main.route) {
        MainScreen(navController = appContainerState.navController)
    }
    composable(Destination.Image.route) { ImageScreen() }
    addTweakGraph(
        navController = appContainerState.navController,
    )
}

sealed class Destination(val route: String) {
    data object Main : Destination("main")
    data object Image : Destination("image") {
        const val ARGUMENT_KEY = "imageUrl"
    }
}