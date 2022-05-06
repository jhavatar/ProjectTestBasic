package io.chthonic.projecttestbasic.domain.data

interface DogImageRepository {
    suspend fun getDogImageUrl(): String
}