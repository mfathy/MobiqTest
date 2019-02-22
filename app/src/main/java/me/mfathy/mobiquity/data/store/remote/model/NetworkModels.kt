package me.mfathy.mobiquity.data.store.remote.model


import com.google.gson.annotations.SerializedName

/**
 * Remote data store models.
 *
 * These classes should be used in this layer only.
 */

data class NetPrice(
    @SerializedName("amount")
    val amount: String = "",
    @SerializedName("currency")
    val currency: String = ""
)


data class NetProduct(
    @SerializedName("salePrice")
    val salePrice: NetPrice,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("categoryId")
    val categoryId: String = "",
    @SerializedName("url")
    val url: String = ""
)


data class NetCategory(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("products")
    val products: List<NetProduct>?
)


