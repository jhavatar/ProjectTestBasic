package io.chthonic.projecttestbasic.domain

interface GetDogImageUsecase {
    suspend fun execute(): GetDogImageUsecaseImpl.DogImage
}