package io.chthonic.projecttestbasic.data

import io.chthonic.projecttestbasic.domain.DogImageRepository
import io.chthonic.projecttestbasic.domain.DogImageRepositoryFactory
import io.chthonic.projecttestbasic.domain.config.LocalConfig
import javax.inject.Inject

internal class DogImageRepositoryFactoryImpl @Inject constructor(
    private val ktorImpl: KtorDogImageRepository,
    private val retrofitImpl: RetrofitDogImageRepository,
    private val localConfig: LocalConfig,
) : DogImageRepositoryFactory {
    override suspend fun create(): DogImageRepository =
        if (localConfig.getHttpClient() == LocalConfig.HttpClient.RETROFIT) retrofitImpl else ktorImpl
}