package io.chthonic.projecttestbasic.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.chthonic.projecttestbasic.data.config.LocalConfigImpl
import io.chthonic.projecttestbasic.domain.DogImageRepositoryFactory
import io.chthonic.projecttestbasic.domain.config.LocalConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

private const val BASE_URL = "https://dog.ceo/api/"
private const val BASE_HOST_AND_PATH = "dog.ceo/api"

@Module
@InstallIn(SingletonComponent::class)
internal class DataSingletonModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor(
                logger = HttpLoggingInterceptor.Logger.DEFAULT
            ).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideKtor(): HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.v(message)
                }
            }
            level = LogLevel.ALL
        }
        defaultRequest {
            host = BASE_HOST_AND_PATH
            url {
                protocol = URLProtocol.HTTP
            }
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    @Provides
    fun provideDogImageRepositoryFactory(
        impl: DogImageRepositoryFactoryImpl,
    ): DogImageRepositoryFactory = impl

    @Provides
    fun provideLocalConfig(
        impl: LocalConfigImpl,
    ): LocalConfig = impl
}