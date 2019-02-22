package me.mfathy.mobiquity.factory

import me.mfathy.mobiquity.data.model.CategoryEntity
import me.mfathy.mobiquity.data.model.ProductEntity
import me.mfathy.mobiquity.data.model.SalePriceEntity
import me.mfathy.mobiquity.data.store.remote.model.NetCategory
import me.mfathy.mobiquity.data.store.remote.model.NetPrice
import me.mfathy.mobiquity.data.store.remote.model.NetProduct
import me.mfathy.mobiquity.domain.model.Category
import me.mfathy.mobiquity.domain.model.Product
import me.mfathy.mobiquity.domain.model.SalePrice

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Data factory class to generate fake object of CategoryEntity, ProductEntity, SalePriceEntity
 */
object ProductDataFactory {

    fun makeCategoryEntity(): CategoryEntity = CategoryEntity(
        name = DataFactory.randomString(),
        description = DataFactory.randomString(),
        id = DataFactory.randomString(),
        productEntities = makeProductEntityList(2)
    )

    private fun makeProductEntityList(count: Int): List<ProductEntity>? {
        val list = mutableListOf<ProductEntity>()
        repeat(count) {
            list.add(makeProductEntity())
        }

        return list.toList()
    }

    private fun makeProductEntity(): ProductEntity = ProductEntity(
        salePriceEntity = makeSalePriceEntity(),
        id = DataFactory.randomString(),
        description = DataFactory.randomString(),
        name = DataFactory.randomString(),
        categoryId = DataFactory.randomString(),
        url = DataFactory.randomString()
    )

    private fun makeSalePriceEntity(): SalePriceEntity = SalePriceEntity(
        amount = DataFactory.randomString(),
        currency = DataFactory.randomString()
    )

    fun makeNetCategory(): NetCategory = NetCategory(
        name = DataFactory.randomString(),
        description = DataFactory.randomString(),
        id = DataFactory.randomString(),
        products = makeProductList(2)
    )

    private fun makeProductList(count: Int): List<NetProduct>? {
        val list = mutableListOf<NetProduct>()
        repeat(count) {
            list.add(makeNetProduct())
        }

        return list.toList()
    }

    private fun makeNetProduct(): NetProduct = NetProduct(
        salePrice = makeNetSalePrice(),
        id = DataFactory.randomString(),
        description = DataFactory.randomString(),
        name = DataFactory.randomString(),
        categoryId = DataFactory.randomString(),
        url = DataFactory.randomString()
    )

    private fun makeNetSalePrice(): NetPrice = NetPrice(
        amount = DataFactory.randomString(),
        currency = DataFactory.randomString()
    )

    fun makeCategory(): Category = Category(
        name = DataFactory.randomString(),
        description = DataFactory.randomString(),
        id = DataFactory.randomString(),
        products = makeProductsList(2)
    )

    private fun makeProductsList(count: Int): List<Product>? {
        val list = mutableListOf<Product>()
        repeat(count) {
            list.add(makeProduct())
        }

        return list.toList()
    }

    fun makeProduct(): Product = Product(
        salePrice = makeSalePrice(),
        id = DataFactory.randomString(),
        description = DataFactory.randomString(),
        name = DataFactory.randomString(),
        categoryId = DataFactory.randomString(),
        url = DataFactory.randomString()
    )

    private fun makeSalePrice(): SalePrice = SalePrice(
        amount = DataFactory.randomString(),
        currency = DataFactory.randomString()
    )

}