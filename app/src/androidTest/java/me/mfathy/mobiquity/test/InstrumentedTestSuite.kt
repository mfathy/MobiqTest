package me.mfathy.mobiquity.test

import me.mfathy.mobiquity.exception.ErrorMessageFactoryTest
import me.mfathy.mobiquity.ui.products.ProductDetailsActivityTest
import me.mfathy.mobiquity.ui.products.ProductsActivityTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Run all instrumented tests including UI.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    ErrorMessageFactoryTest::class,
    ProductsActivityTest::class,
    ProductDetailsActivityTest::class
)
class InstrumentedTestSuite