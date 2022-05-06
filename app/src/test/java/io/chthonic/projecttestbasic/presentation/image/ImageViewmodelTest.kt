package io.chthonic.projecttestbasic.presentation.image

import android.os.Bundle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.assertEquals

class ImageViewmodelTest {
    val tested = ImageViewModel()

    @Test
    fun `when onCreate is called with valid param then imageUrlToShow state is updated`() =
        runTest {
            // given
            val arguments = mock<Bundle> {
                on { getString("imageUrl", null) } doReturn "foo"
            }

            // when
            tested.onCreate(arguments)

            // then
            assertEquals("foo", tested.imageUrlToShow.value)
        }
}