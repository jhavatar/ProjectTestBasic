package io.chthonic.projecttestbasic.data.config

data object LocalConfig {
    enum class HttpClient {
        KTOR,
        RETROFIT
    }

    val httpClient: HttpClient = HttpClient.KTOR
}