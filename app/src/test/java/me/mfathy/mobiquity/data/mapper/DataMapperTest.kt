package me.mfathy.mobiquity.data.mapper


import me.mfathy.mobiquity.data.model.CategoryEntity
import me.mfathy.mobiquity.data.model.ProductEntity
import me.mfathy.mobiquity.data.model.SalePriceEntity
import me.mfathy.mobiquity.domain.model.Category
import me.mfathy.mobiquity.domain.model.Product
import me.mfathy.mobiquity.domain.model.SalePrice
import me.mfathy.mobiquity.factory.ProductDataFactory
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * Unit test for data mapper.
 */
class DataMapperTest {

    private val mapper = DataMapper()

    @Test
    fun testMapFromEntityMapsData() {
        val entity = ProductDataFactory.makeCategoryEntity()
        val domain = mapper.mapFromEntity(entity)

        assertDataAreEqual(entity, domain)
    }

    private fun assertDataAreEqual(entity: CategoryEntity, domain: Category) {
        assertEquals(entity.description, domain.description)
        assertEquals(entity.id, domain.id)
        assertEquals(entity.name, domain.name)
        assertEquals(entity.productEntities?.size, domain.products?.size)

        assertProductsAreEqual(entity.productEntities, domain.products)
    }

    private fun assertProductsAreEqual(productEntities: List<ProductEntity>?, products: List<Product>?) {
        if (productEntities?.isNotEmpty()!! && products?.isNotEmpty()!!) {
            val entity = productEntities[0]
            val domain = products[0]

            assertEquals(entity.categoryId, domain.categoryId)
            assertEquals(entity.description, domain.description)
            assertEquals(entity.id, domain.id)
            assertEquals(entity.name, domain.name)
            assertEquals(entity.url, domain.url)

            assertPricesAreEqual(entity.salePriceEntity, domain.salePrice)
        }
    }

    private fun assertPricesAreEqual(salePriceEntity: SalePriceEntity, salePrice: SalePrice) {
        assertEquals(salePriceEntity.amount, salePrice.amount)
        assertEquals(salePriceEntity.currency, salePrice.currency)
    }
}