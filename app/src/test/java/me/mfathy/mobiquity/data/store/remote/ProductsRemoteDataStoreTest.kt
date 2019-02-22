package me.mfathy.mobiquity.data.store.remote

import io.reactivex.Single
import me.mfathy.mobiquity.any
import me.mfathy.mobiquity.data.mapper.RemoteDataMapper
import me.mfathy.mobiquity.data.model.CategoryEntity
import me.mfathy.mobiquity.data.store.remote.model.NetCategory
import me.mfathy.mobiquity.data.store.remote.service.RemoteServiceApi
import me.mfathy.mobiquity.factory.ProductDataFactory
import org.junit.Test
import org.mockito.Mockito.*


/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Unit test for ProductsRemoteDataStore with the following assumptions:
 * - Getting categories request completes.
 * - Getting categories request returns data.
 * - Getting categories request calls Server.
 */
class ProductsRemoteDataStoreTest {

    private val mockServiceApi = mock(RemoteServiceApi::class.java)
    private val mockDataMapper = mock(RemoteDataMapper::class.java)

    private val remote = ProductsRemoteDataStore(
        mockServiceApi,
        mockDataMapper
    )

    private val mapper = RemoteDataMapper()

    @Test
    fun testGetCategoriesCompletes() {
        val netCategory = ProductDataFactory.makeNetCategory()

        stubRemoteApiGetCategories(listOf(netCategory))

        val testObserver = remote.getCategories().test()
        testObserver.assertComplete()
    }

    @Test
    fun testGetCategoriesReturnsData() {
        val netCategory = ProductDataFactory.makeNetCategory()
        val entity = mapper.mapFromEntity(netCategory)
        stubRemoteApiGetCategories(listOf(netCategory))
        stubRemoteMapperMapFromEntity(entity)
        val testObserver = remote.getCategories().test()
        testObserver.assertValue(listOf(entity))
    }

    @Test
    fun testGetCategoriesCallsServer() {
        val netCategory = ProductDataFactory.makeNetCategory()

        stubRemoteApiGetCategories(listOf(netCategory))

        remote.getCategories().test()

        verify(mockServiceApi, times(1)).getCategories()
        verify(mockDataMapper, times(1)).mapFromEntity(any())
    }

    private fun stubRemoteApiGetCategories(listEntities: List<NetCategory>) {
        `when`(mockServiceApi.getCategories()).thenReturn(Single.just(listEntities))
    }

    private fun stubRemoteMapperMapFromEntity(entity: CategoryEntity) {
        `when`(mockDataMapper.mapFromEntity(any()))
            .thenReturn(entity)
    }
}