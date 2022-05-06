package io.chthonic.projecttestbasic.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainSingletonModule {

    @Binds
    @Singleton
    abstract fun bindsGetDogImageUsecase(impl: GetDogImageUsecaseImpl): GetDogImageUsecase
}