package io.chthonic.projecttestbasic.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.chthonic.projecttestbasic.data.api.DogImageApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class DogImageRepository @Inject constructor(retrofit: Retrofit) {

    private val dogImageApi: DogImageApi by lazy {
        retrofit.create(DogImageApi::class.java)
    }

    suspend fun getDogImageUrl(): String =
        dogImageApi.getRandomDogImage().message ?: throw NullPointerException()
}