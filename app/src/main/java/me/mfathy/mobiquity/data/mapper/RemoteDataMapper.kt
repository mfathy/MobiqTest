package me.mfathy.mobiquity.data.mapper

import me.mfathy.mobiquity.data.model.CategoryEntity
import me.mfathy.mobiquity.data.model.ProductEntity
import me.mfathy.mobiquity.data.model.SalePriceEntity
import me.mfathy.mobiquity.data.store.remote.model.NetCategory
import me.mfathy.mobiquity.data.store.remote.model.NetPrice
import me.mfathy.mobiquity.data.store.remote.model.NetProduct

/**
 * RemoteDataMapper maps data between remote data store and data repository.
 * This should map data from NetCategory to CategoryEntity.
 */
open class RemoteDataMapper : EntityMapper<NetCategory, CategoryEntity> {
    override fun mapFromEntity(entity: NetCategory): CategoryEntity = CategoryEntity(
        name = entity.name,
        id = entity.id,
        description = entity.description,
        productEntities = entity.products?.map {
            mapFromNetProduct(it)
        }
    )

    /**
     * This should map data from NetProduct to ProductEntity.
     */
    private fun mapFromNetProduct(product: NetProduct): ProductEntity = ProductEntity(
        salePriceEntity = mapFromNetSalPrice(product.salePrice),
        description = product.description,
        id = product.id,
        categoryId = product.categoryId,
        name = product.name,
        url = product.url
    )

    /**
     * This should map data from NetPrice to SalePriceEntity.
     */
    private fun mapFromNetSalPrice(salePrice: NetPrice): SalePriceEntity = SalePriceEntity(
        amount = salePrice.amount,
        currency = salePrice.currency
    )
}