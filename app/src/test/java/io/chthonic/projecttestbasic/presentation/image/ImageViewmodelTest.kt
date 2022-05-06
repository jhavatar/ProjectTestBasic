package io.chthonic.projecttestbasic.presentation.image

import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class ImageViewmodelTest {
    val tested = ImageViewModel()

    @Test
    fun `when onCreate is called with valid param then imageUrlToShow state is updated`() =
        runTest {
            // given
            val argument = "foo"

            // when
            tested.onCreate(argument)

            // then
            assertEquals("foo", tested.imageUrlToShow.value)
        }
}