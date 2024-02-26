package io.chthonic.projecttestbasic.data

import io.chthonic.projecttestbasic.data.api.DogImageApi
import io.chthonic.projecttestbasic.domain.DogImageRepository
import retrofit2.Retrofit
import javax.inject.Inject

internal class RetrofitDogImageRepository @Inject constructor(retrofit: Retrofit) :
    DogImageRepository {

    private val dogImageApi: DogImageApi by lazy {
        retrofit.create(DogImageApi::class.java)
    }

    override suspend fun getDogImageUrl(): String =
        dogImageApi.getRandomDogImage().message ?: throw NullPointerException()
}