package io.chthonic.projecttestbasic.presentation.image

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ImageViewmodelTest {
    val savedState: SavedStateHandle = mock {
        on { get<String>("imageUrl") }  doReturn "foo"
    }
    val tested = ImageViewModel(savedState)

    @Test
    fun `when initialize with valid param then imageUrlToShow state is updated`() =
        runTest {
            // when / then
            assertEquals("foo", tested.imageUrlToShow.value)
        }
}