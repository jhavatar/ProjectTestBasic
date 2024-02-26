package io.chthonic.projecttestbasic.data

import io.chthonic.projecttestbasic.data.model.DogImageResult
import io.chthonic.projecttestbasic.domain.DogImageRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

internal class KtorDogImageRepository @Inject constructor(private val httpClient: HttpClient) :
    DogImageRepository {

    override suspend fun getDogImageUrl(): String =
        httpClient.get(
            urlString = "breeds/image/random/",
        ).body<DogImageResult>().message ?: throw NullPointerException()
}