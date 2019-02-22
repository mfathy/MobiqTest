package me.mfathy.mobiquity.ui.products

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import me.mfathy.mobiquity.R
import me.mfathy.mobiquity.mock.server.MockServerDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Espresso ui test to ensure that ProductsActivityTest is working right.
 */
@LargeTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class ProductsActivityTest {

    @Rule
    @JvmField
    val mActivityTestRule = ActivityTestRule<ProductsActivity>(ProductsActivity::class.java, false, false)

    private lateinit var mockWebServer: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testProductActivityDrawCorrectResponse() {

        mockWebServer.setDispatcher(MockServerDispatcher().RequestDispatcher())

        mActivityTestRule.launchActivity(Intent())

        Thread.sleep(2000)

        onView(withId(R.id.recyclerview)).check(
            ViewAssertions.matches(hasDescendant(withText("Food")))
        )

        onView(withId(R.id.recyclerview)).check(
            ViewAssertions.matches(hasDescendant(withText("Bread")))
        )
    }

    @Test
    fun testProductActivityDrawErrorMessage() {

        mockWebServer.setDispatcher(MockServerDispatcher().ErrorDispatcher())

        mActivityTestRule.launchActivity(Intent())

        Thread.sleep(2000)

        onView(withId(R.id.textViewError)).check(ViewAssertions.matches(withText("Server error!")))
    }

}