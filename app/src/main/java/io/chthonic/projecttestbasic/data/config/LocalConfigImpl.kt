package io.chthonic.projecttestbasic.data.config

import com.telefonica.tweaks.Tweaks
import io.chthonic.projecttestbasic.data.ktx.findByName
import io.chthonic.projecttestbasic.domain.config.LocalConfig
import javax.inject.Inject

class LocalConfigImpl @Inject constructor() : LocalConfig {
    override suspend fun getHttpClient(): LocalConfig.HttpClient =
        (LocalConfig.HttpClient.entries findByName Tweaks.getReference()
            .getTweak(LocalConfig.httpClient.key)) ?: LocalConfig.httpClient.defaultValue
}