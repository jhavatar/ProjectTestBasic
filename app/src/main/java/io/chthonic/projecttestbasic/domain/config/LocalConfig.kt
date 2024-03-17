package io.chthonic.projecttestbasic.domain.config

interface LocalConfig {

    data class ConfigValue<T>(
        val key: String,
        val defaultValue: T
    )

    enum class HttpClient {
        KTOR,
        RETROFIT
    }

    companion object {
        val httpClient = ConfigValue(key = "httpClient", defaultValue = HttpClient.RETROFIT)
    }

    suspend fun getHttpClient(): HttpClient
}