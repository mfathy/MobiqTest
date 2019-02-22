package me.mfathy.mobiquity.domain.interactor.products

import io.reactivex.Single
import me.mfathy.mobiquity.domain.executor.ExecutionThread
import me.mfathy.mobiquity.domain.model.Category
import me.mfathy.mobiquity.domain.repository.ProductsRepository
import me.mfathy.mobiquity.factory.ProductDataFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * Unit test for GetProducts use case with the following assumptions:
 * - Running the use case with complete.
 * - Running the use case will call the repository.
 */
@RunWith(MockitoJUnitRunner::class)
class GetProductsTest {
    private lateinit var mGetProducts: GetProducts

    @Mock
    lateinit var mockDataRepository: ProductsRepository
    @Mock
    lateinit var mockExecutionThread: ExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mGetProducts = GetProducts(mockDataRepository, mockExecutionThread)
    }

    @Test
    fun testGetProductsCompletes() {
        val category = ProductDataFactory.makeCategory()
        stubDataRepositoryGetProducts(category)
        val testObserver = mGetProducts.buildUseCaseSingle().test()
        testObserver.assertComplete()
    }

    @Test
    fun testGetProductsCallsRepository() {
        val category = ProductDataFactory.makeCategory()
        stubDataRepositoryGetProducts(category)

        mGetProducts.buildUseCaseSingle().test()

        Mockito.verify(mockDataRepository).getCategories()
    }

    private fun stubDataRepositoryGetProducts(category: Category) {
        Mockito.`when`(mockDataRepository.getCategories()).thenReturn(
            Single.just(
                listOf(category)
            )
        )
    }
}