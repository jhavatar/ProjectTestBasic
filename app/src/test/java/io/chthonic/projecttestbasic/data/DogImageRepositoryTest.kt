package io.chthonic.projecttestbasic.data

import io.chthonic.projecttestbasic.data.api.DogImageApi
import io.chthonic.projecttestbasic.data.model.DogImageResult
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.Retrofit
import kotlin.test.assertEquals

class DogImageRepositoryTest {

    val message = "foo"
    val dogImageResult = DogImageResult(
        message = "foo",
        status = "success"
    )
    val dogImageApi: DogImageApi = mock {
        on { runBlocking { getRandomDogImage() } } doReturn dogImageResult
    }
    val retrofit: Retrofit = mock {
        on { create(DogImageApi::class.java) } doReturn dogImageApi
    }
    val tested =  DogImageRepository(retrofit)

    @Test
    fun `when DogImageResult then return message as url string`() = runTest {
        assertEquals(tested.getDogImageUrl(), message)
    }
}