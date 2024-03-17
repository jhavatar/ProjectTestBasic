package io.chthonic.projecttestbasic

import android.app.Application
import com.telefonica.tweaks.Tweaks
import dagger.hilt.android.HiltAndroidApp
import io.chthonic.projecttestbasic.presentation.config.genLocalConfigScreen
import timber.log.Timber
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
        Tweaks.init(this, genLocalConfigScreen())
    }
}