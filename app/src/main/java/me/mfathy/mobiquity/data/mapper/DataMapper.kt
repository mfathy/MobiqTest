package me.mfathy.mobiquity.data.mapper

import me.mfathy.mobiquity.data.model.CategoryEntity
import me.mfathy.mobiquity.data.model.ProductEntity
import me.mfathy.mobiquity.data.model.SalePriceEntity
import me.mfathy.mobiquity.domain.model.Category
import me.mfathy.mobiquity.domain.model.Product
import me.mfathy.mobiquity.domain.model.SalePrice
import javax.inject.Inject

/**
 * DataMapper maps data between data layer and domain layer.
 * This should map data from CategoryEntity to Category in domain layer.
 */
open class DataMapper @Inject constructor() : EntityMapper<CategoryEntity, Category> {

    /**
     * This should map data from CategoryEntity to Category in domain layer.
     */
    override fun mapFromEntity(entity: CategoryEntity): Category = Category(
        name = entity.name,
        id = entity.id,
        description = entity.description,
        products = entity.productEntities?.map {
            mapFromProductEntity(it)
        }
    )

    /**
     * This should map data from ProductEntity to Product in domain layer.
     */
    private fun mapFromProductEntity(entity: ProductEntity): Product = Product(
        salePrice = mapFromSalePriceEntity(entity.salePriceEntity),
        description = entity.description,
        id = entity.id,
        name = entity.name,
        categoryId = entity.categoryId,
        url = entity.url
    )

    /**
     * This should map data from SalePriceEntity to SalePrice in domain layer.
     */
    private fun mapFromSalePriceEntity(entity: SalePriceEntity): SalePrice = SalePrice(
        amount = entity.amount,
        currency = entity.currency
    )

}