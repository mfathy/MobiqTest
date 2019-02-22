package me.mfathy.mobiquity.data.mapper

import me.mfathy.mobiquity.data.model.CategoryEntity
import me.mfathy.mobiquity.data.model.ProductEntity
import me.mfathy.mobiquity.data.model.SalePriceEntity
import me.mfathy.mobiquity.data.store.remote.model.NetCategory
import me.mfathy.mobiquity.data.store.remote.model.NetPrice
import me.mfathy.mobiquity.data.store.remote.model.NetProduct
import me.mfathy.mobiquity.factory.ProductDataFactory
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * Unit tests for remote data mapper.
 */
class RemoteDataMapperTest {

    private val mapper = RemoteDataMapper()

    @Test
    fun testMapFromEntityMapsData() {

        val entity = ProductDataFactory.makeNetCategory()
        val domain = mapper.mapFromEntity(entity)

        assertDataAreEqual(entity, domain)

    }

    private fun assertDataAreEqual(entity: NetCategory, domain: CategoryEntity) {
        assertEquals(entity.description, domain.description)
        assertEquals(entity.id, domain.id)
        assertEquals(entity.name, domain.name)
        assertEquals(entity.products?.size, domain.productEntities?.size)

        assertProductsAreEqual(entity.products, domain.productEntities)
    }

    private fun assertProductsAreEqual(productEntities: List<NetProduct>?, products: List<ProductEntity>?) {
        if (productEntities?.isNotEmpty()!! && products?.isNotEmpty()!!) {
            val entity = productEntities[0]
            val domain = products[0]

            assertEquals(entity.categoryId, domain.categoryId)
            assertEquals(entity.description, domain.description)
            assertEquals(entity.id, domain.id)
            assertEquals(entity.name, domain.name)
            assertEquals(entity.url, domain.url)

            assertPricesAreEqual(entity.salePrice, domain.salePriceEntity)
        }
    }

    private fun assertPricesAreEqual(salePriceEntity: NetPrice, salePrice: SalePriceEntity) {
        assertEquals(salePriceEntity.amount, salePrice.amount)
        assertEquals(salePriceEntity.currency, salePrice.currency)
    }
}