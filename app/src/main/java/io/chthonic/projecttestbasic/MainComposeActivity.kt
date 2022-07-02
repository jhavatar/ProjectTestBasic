package io.chthonic.projecttestbasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.chthonic.projecttestbasic.presentation.image.ImageScreen
import io.chthonic.projecttestbasic.presentation.main.MainScreen

@AndroidEntryPoint
class MainComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(
                topBar = {
                    // your top bar
                    TopAppBar(title = { Text(stringResource(R.string.app_name)) })
                },
                floatingActionButton = {
                    // your floating action button
                },
                drawerContent = {
                    // drawer content
                },
                content = {
                    // your page content
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Destination.Main.route,
                    ) {
                        composable(Destination.Main.route) { MainScreen(navController = navController) }
                        composable(Destination.Image.route) { ImageScreen() }
                    }
                },
                bottomBar = {
                    // your bottom bar composable
                }
            )
        }
    }
}


sealed class Destination(val route: String) {
    object Main : Destination("main")
    object Image : Destination("image")
}