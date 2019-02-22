package me.mfathy.mobiquity.test

import me.mfathy.mobiquity.data.mapper.DataMapperTest
import me.mfathy.mobiquity.data.mapper.RemoteDataMapperTest
import me.mfathy.mobiquity.data.repository.ProductsDataRepositoryTest
import me.mfathy.mobiquity.data.store.remote.ProductsRemoteDataStoreTest
import me.mfathy.mobiquity.data.store.remote.RemoteServiceApiTest
import me.mfathy.mobiquity.data.store.remote.utils.NetworkConnectionTest
import me.mfathy.mobiquity.domain.interactor.products.GetProductsTest
import me.mfathy.mobiquity.ui.products.ProductsViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * Run all unit tests of the project at once.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    DataMapperTest::class,
    RemoteDataMapperTest::class,
    NetworkConnectionTest::class,
    ProductsRemoteDataStoreTest::class,
    RemoteServiceApiTest::class,
    GetProductsTest::class,
    ProductsViewModelTest::class,
    ProductsDataRepositoryTest::class
)
class UnitTestSuite