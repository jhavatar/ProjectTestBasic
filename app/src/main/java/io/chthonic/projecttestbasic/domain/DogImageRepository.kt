package io.chthonic.projecttestbasic.domain

interface DogImageRepository {
    suspend fun getDogImageUrl(): String
}