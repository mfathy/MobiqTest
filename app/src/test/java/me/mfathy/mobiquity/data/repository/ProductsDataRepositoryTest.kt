package me.mfathy.mobiquity.data.repository

import io.reactivex.Single
import me.mfathy.mobiquity.any
import me.mfathy.mobiquity.data.mapper.DataMapper
import me.mfathy.mobiquity.data.model.CategoryEntity
import me.mfathy.mobiquity.data.store.ProductsDataStore
import me.mfathy.mobiquity.factory.ProductDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Unit tests for ProductsDataRepository
 *
 * Test assumptions:
 * - Getting categories request completes.
 * - Getting categories request calls store.
 */
@RunWith(MockitoJUnitRunner::class)
class ProductsDataRepositoryTest {

    private val mockDataStore = mock(ProductsDataStore::class.java)
    private val mockMapper = mock(DataMapper::class.java)

    private val repository = ProductsDataRepository(mockDataStore, mockMapper)

    @Test
    fun testGetCategoriesCompletes() {

        val entity = ProductDataFactory.makeCategoryEntity()
        stubDataStoreGetCategories(listOf(entity))
        val testObserver = repository.getCategories().test()
        testObserver.assertComplete()
    }

    @Test
    fun testGetCategoriesCallsStore() {

        val entity = ProductDataFactory.makeCategoryEntity()
        val listOfEntities = listOf(entity, entity)
        stubDataStoreGetCategories(listOfEntities)

        repository.getCategories().test()

        verify(mockDataStore, times(1)).getCategories()
        verify(mockMapper, times(listOfEntities.size)).mapFromEntity(any())
    }

    private fun stubDataStoreGetCategories(CallsStore: List<CategoryEntity>) {
        `when`(mockDataStore.getCategories()).thenReturn(Single.just(CallsStore))
    }
}