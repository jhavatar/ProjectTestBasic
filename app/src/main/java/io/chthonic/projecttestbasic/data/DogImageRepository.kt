package io.chthonic.projecttestbasic.data

import io.chthonic.projecttestbasic.data.api.DogImageApi
import retrofit2.Retrofit
import javax.inject.Inject

class DogImageRepository @Inject constructor(retrofit: Retrofit) {

    private val dogImageApi: DogImageApi by lazy {
        retrofit.create(DogImageApi::class.java)
    }

    suspend fun getDogImageUrl(): String =
        dogImageApi.getRandomDogImage().message ?: throw NullPointerException()
}