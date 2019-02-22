package me.mfathy.mobiquity.data.store.remote

import io.reactivex.Single
import me.mfathy.mobiquity.data.mapper.RemoteDataMapper
import me.mfathy.mobiquity.data.model.CategoryEntity
import me.mfathy.mobiquity.data.store.ProductsDataStore
import me.mfathy.mobiquity.data.store.remote.service.RemoteServiceApi
import javax.inject.Inject

/**
 * ProductsDataStore implementation for remote data store.
 */
class ProductsRemoteDataStore @Inject constructor(
    private val serviceApi: RemoteServiceApi,
    private val mapper: RemoteDataMapper
) : ProductsDataStore {
    override fun getCategories(): Single<List<CategoryEntity>> {
        return serviceApi.getCategories().map { netCategories ->
            netCategories.map { category ->
                mapper.mapFromEntity(category)
            }
        }
    }
}