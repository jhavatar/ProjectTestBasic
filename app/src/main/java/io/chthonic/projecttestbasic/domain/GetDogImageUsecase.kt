package io.chthonic.projecttestbasic.domain

import javax.inject.Inject

class GetDogImageUsecase @Inject constructor(
    private val dogImageRepoFactory: DogImageRepositoryFactory
) {

    data class DogImage(
        val url: String
    )

    suspend fun execute(): DogImage = DogImage(dogImageRepoFactory.create().getDogImageUrl())
}