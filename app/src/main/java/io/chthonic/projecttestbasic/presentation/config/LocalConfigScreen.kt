package io.chthonic.projecttestbasic.presentation.config

import com.telefonica.tweaks.domain.TweaksGraph
import com.telefonica.tweaks.domain.tweaksGraph
import io.chthonic.projecttestbasic.domain.config.LocalConfig
import io.chthonic.projecttestbasic.domain.config.LocalConfig.HttpClient.KTOR
import io.chthonic.projecttestbasic.domain.config.LocalConfig.HttpClient.RETROFIT
import kotlinx.coroutines.flow.flowOf

fun genLocalConfigScreen(): TweaksGraph = tweaksGraph {
    cover("Tweaks") {
        dropDownMenu(
            key = LocalConfig.httpClient.key,
            name = "HTTP Client",
            values = listOf(
                RETROFIT.name,
                KTOR.name
            ),
            defaultValue = flowOf(LocalConfig.httpClient.defaultValue.name)
        )
    }
}