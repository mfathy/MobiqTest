package me.mfathy.mobiquity.data.repository

import io.reactivex.Single
import me.mfathy.mobiquity.data.mapper.DataMapper
import me.mfathy.mobiquity.data.store.ProductsDataStore
import me.mfathy.mobiquity.domain.model.Category
import me.mfathy.mobiquity.domain.repository.ProductsRepository
import javax.inject.Inject

/**
 * ProductsRepository implementation.
 * This class ties domain layer with data store.
 */
class ProductsDataRepository @Inject constructor(
    private val dataStore: ProductsDataStore,
    private val mapper: DataMapper
) : ProductsRepository {
    override fun getCategories(): Single<List<Category>> {
        return dataStore.getCategories().map { entities ->
            entities.map { mapper.mapFromEntity(it) }
        }
    }

}