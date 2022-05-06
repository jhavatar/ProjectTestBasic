package io.chthonic.projecttestbasic.domain

import io.chthonic.projecttestbasic.domain.data.DogImageRepository
import javax.inject.Inject

class GetDogImageUsecaseImpl @Inject constructor(
    private val dogImageRepo: DogImageRepository
) : GetDogImageUsecase {

    data class DogImage(
        val url: String
    )

    override suspend fun execute(): DogImage = DogImage(dogImageRepo.getDogImageUrl())
}