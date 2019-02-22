package me.mfathy.mobiquity.ui.products

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import me.mfathy.mobiquity.R
import me.mfathy.mobiquity.factory.ProductDataFactory
import me.mfathy.mobiquity.ui.products.details.ProductDetailsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Espresso ui test to ensure that ProductsActivityTest is working right.
 */
@LargeTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class ProductDetailsActivityTest {

    private val product = ProductDataFactory.makeProduct()
    private val name = product.name
    private val price = "${product.salePrice.amount} ${product.salePrice.currency}"

    @Rule
    @JvmField
    val mActivityTestRule = ActivityTestRule<ProductDetailsActivity>(
        ProductDetailsActivity::class.java,
        false,
        false
    )

    @Test
    fun testProductDetailsActivity() {

        mActivityTestRule.launchActivity(createIntentWithExtras())
        Thread.sleep(1000)
        onView(withId(R.id.textViewDetailsName)).check(ViewAssertions.matches(withText(name)))
        onView(withId(R.id.textViewDetailsPrice)).check(ViewAssertions.matches(withText(price)))
    }

    private fun createIntentWithExtras(): Intent? {
        val startIntent =
            Intent(InstrumentationRegistry.getInstrumentation().targetContext, ProductDetailsActivity::class.java)
        startIntent.putExtra(ProductDetailsActivity.EXTRA_PRODUCT_NAME, product.name)
        startIntent.putExtra(ProductDetailsActivity.EXTRA_PRODUCT_IMAGE_URL, product.url)
        startIntent.putExtra(
            ProductDetailsActivity.EXTRA_PRODUCT_PRICE,
            "${product.salePrice.amount} ${product.salePrice.currency}"
        )

        return startIntent
    }
}