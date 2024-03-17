package io.chthonic.projecttestbasic.domain

interface DogImageRepositoryFactory {
    suspend fun create() : DogImageRepository
}