package io.chthonic.projecttestbasic

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import io.chthonic.projecttestbasic.presentation.AppContainer
import io.chthonic.projecttestbasic.presentation.theme.AppTheme

@AndroidEntryPoint
class MainComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(isDarkTheme = isNightMode()) {
                AppContainer()
            }
        }
    }

    private fun isNightMode(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            resources.configuration.isNightModeActive
        } else {
            resources.configuration.uiMode == Configuration.UI_MODE_NIGHT_YES
        }
}