package io.chthonic.projecttestbasic.data.api

import io.chthonic.projecttestbasic.data.model.DogImageResult
import retrofit2.http.GET

interface DogImageApi {
    @GET("breeds/image/random/")
    suspend fun getRandomDogImage(): DogImageResult
}