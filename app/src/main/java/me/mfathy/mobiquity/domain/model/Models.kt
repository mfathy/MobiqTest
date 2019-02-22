package me.mfathy.mobiquity.domain.model

/**
 * Domain layer models.
 *
 * These classes should be used in this layer and presentation layer.
 */

data class Category(
    val name: String = "",
    val description: String = "",
    val id: String = "",
    val products: List<Product>?
)

data class SalePrice(
    val amount: String = "",
    val currency: String = ""
)


data class Product(
    val salePrice: SalePrice,
    val name: String = "",
    val description: String = "",
    val id: String = "",
    val categoryId: String = "",
    val url: String = ""
)


