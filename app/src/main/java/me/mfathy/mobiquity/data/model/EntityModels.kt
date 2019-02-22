package me.mfathy.mobiquity.data.model

/**
 * Data layer models.
 *
 * These classes should be used in this layer only.
 */

data class CategoryEntity(
    val name: String = "",
    val description: String = "",
    val id: String = "",
    val productEntities: List<ProductEntity>?
)


data class SalePriceEntity(
    val amount: String = "",
    val currency: String = ""
)


data class ProductEntity(
    val salePriceEntity: SalePriceEntity,
    val name: String = "",
    val description: String = "",
    val id: String = "",
    val categoryId: String = "",
    val url: String = ""
)


