package io.chthonic.projecttestbasic.domain

import io.chthonic.projecttestbasic.data.DogImageRepository
import javax.inject.Inject

class GetDogImageUsecase @Inject constructor(
    private val dogImageRepo: DogImageRepository
) {

    data class DogImage(
        val url: String
    )

    suspend fun execute(): DogImage = DogImage(dogImageRepo.getDogImageUrl())
}