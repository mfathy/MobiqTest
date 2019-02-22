package me.mfathy.mobiquity.domain.repository

import io.reactivex.Single
import me.mfathy.mobiquity.domain.model.Category

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Data repository contact to define layer actions.
 */

interface ProductsRepository {
    /**
     * Return a single which will emits a list of categories otherwise an error.
     */
    fun getCategories(): Single<List<Category>>
}