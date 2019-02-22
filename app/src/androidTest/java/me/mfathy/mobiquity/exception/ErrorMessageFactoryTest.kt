package me.mfathy.mobiquity.exception

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import me.mfathy.mobiquity.R
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Unit test for ErrorMessageFactory
 * Test Assumptions:
 * - Should returns the correct message based on the exception.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class ErrorMessageFactoryTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun testNetworkConnectionMessage() {
        val expectedMessage = context.resources.getString(R.string.network_error)
        val actualMessage = ErrorMessageFactory.create(context, IOException())

        assertEquals(actualMessage, expectedMessage)
    }
}