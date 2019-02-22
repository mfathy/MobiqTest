package me.mfathy.mobiquity.ui.products

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import io.reactivex.Single
import me.mfathy.mobiquity.R
import me.mfathy.mobiquity.factory.ProductDataFactory
import me.mfathy.mobiquity.test.TestApplication
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


/**
 * Espresso ui test to ensure that ProductsActivityTest is working right.
 */
@LargeTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class ProductsActivityTest {

    @Rule
    @JvmField
    val mActivityTestRule = ActivityTestRule<ProductsActivity>(ProductsActivity::class.java, false, false)
    private val category = ProductDataFactory.makeCategory()

    @Before
    fun setUp() {
        stubGetCategories()
    }

    @Test
    fun testProductActivity() {
        mActivityTestRule.launchActivity(null)

        Thread.sleep(3000)

        val categoryNameTextView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.textViewCategoryTitle),
                ViewMatchers.isDisplayed()
            )
        )

        categoryNameTextView.check(ViewAssertions.matches(ViewMatchers.withText(category.name)))

        onView(withId(R.id.recyclerview)).perform(
            RecyclerViewActions.scrollToPosition<ProductsAdapter.ViewHolder>(0)
        )

        onView(withId(R.id.recyclerview)).check(
            ViewAssertions.matches(hasDescendant(withText(category.products?.first()?.name)))
        )

    }

    private fun stubGetCategories() {
        Mockito.`when`(TestApplication.appComponent().repository().getCategories())
            .thenReturn(Single.just(listOf(category)))
    }

}