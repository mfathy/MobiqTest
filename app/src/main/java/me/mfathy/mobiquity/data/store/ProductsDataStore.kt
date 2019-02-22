package me.mfathy.mobiquity.data.store

import io.reactivex.Single
import me.mfathy.mobiquity.data.model.CategoryEntity

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Data Store contact to define layer actions.
 */
interface ProductsDataStore {

    /**
     * Return a single which will emits a list of category entities otherwise an error.
     */
    fun getCategories(): Single<List<CategoryEntity>>
}