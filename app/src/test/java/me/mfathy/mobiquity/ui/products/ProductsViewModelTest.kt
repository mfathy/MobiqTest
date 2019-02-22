package me.mfathy.mobiquity.ui.products

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.observers.DisposableSingleObserver
import me.mfathy.mobiquity.any
import me.mfathy.mobiquity.argumentCaptor
import me.mfathy.mobiquity.capture
import me.mfathy.mobiquity.domain.interactor.products.GetProducts
import me.mfathy.mobiquity.domain.model.Category
import me.mfathy.mobiquity.factory.ProductDataFactory
import me.mfathy.mobiquity.state.DataState
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Unit test for ProductsViewModel
 * Tes Assumptions:
 * - Fetch products executes use case.
 * - Fetch products returns success.
 * - Fetch products returns data.
 * - Fetch products returns error.
 */
@RunWith(JUnit4::class)
class ProductsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private var mockGetProducts = Mockito.mock(GetProducts::class.java)
    private var viewModel = ProductsViewModel(mockGetProducts)

    @Captor
    val captor = argumentCaptor<DisposableSingleObserver<List<Category>>>()

    @Test
    fun testFetchProductsExecutesUseCase() {

        val disposable = viewModel.GetProductsObserver()
        stubProductViewModelAddDisposable(disposable)

        viewModel.fetchProductsList()

        verify(mockGetProducts, Mockito.times(1)).execute(any())
    }

    @Test
    fun testFetchProductsReturnsSuccess() {
        val disposable = viewModel.GetProductsObserver()
        stubProductViewModelAddDisposable(disposable)

        val category = ProductDataFactory.makeCategory()
        viewModel.fetchProductsList()
        verify(mockGetProducts, Mockito.times(1)).execute(capture(captor))
        captor.value.onSuccess(listOf(category))

        assert(viewModel.getProductsLiveData().value is DataState.OnSuccess)
    }

    @Test
    fun testFetchProductsReturnsData() {
        val disposable = viewModel.GetProductsObserver()
        stubProductViewModelAddDisposable(disposable)

        val category = ProductDataFactory.makeCategory()
        viewModel.fetchProductsList()
        verify(mockGetProducts, Mockito.times(1)).execute(capture(captor))
        captor.value.onSuccess(listOf(category))

        assertEqualData(category, viewModel.getProductsLiveData().value)
    }

    @Test
    fun testFetchProductsReturnsError() {
        val disposable = viewModel.GetProductsObserver()
        stubProductViewModelAddDisposable(disposable)

        viewModel.fetchProductsList()
        verify(mockGetProducts, Mockito.times(1)).execute(capture(captor))
        captor.value.onError(RuntimeException())

        assert(viewModel.getProductsLiveData().value is DataState.OnError)
    }

    private fun assertEqualData(expected: Category, value: DataState?) {
        val productsList = value as DataState.OnSuccess

        val actualObject = productsList.data.first()

        assertEquals(expected.name, actualObject.name)
        assertEquals(expected.id, actualObject.id)
        assertEquals(expected.description, actualObject.description)
    }

    private fun stubProductViewModelAddDisposable(disposable: ProductsViewModel.GetProductsObserver) {
        `when`(mockGetProducts.execute(any())).thenReturn(disposable)
    }
}
