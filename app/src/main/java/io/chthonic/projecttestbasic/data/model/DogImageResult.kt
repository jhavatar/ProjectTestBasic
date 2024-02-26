package io.chthonic.projecttestbasic.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DogImageResult(
    val message: String? = null,
    val status: String? = null
)