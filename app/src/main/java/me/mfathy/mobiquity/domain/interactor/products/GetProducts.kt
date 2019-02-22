package me.mfathy.mobiquity.domain.interactor.products

import io.reactivex.Single
import me.mfathy.mobiquity.domain.executor.ExecutionThread
import me.mfathy.mobiquity.domain.interactor.base.SingleUseCase
import me.mfathy.mobiquity.domain.model.Category
import me.mfathy.mobiquity.domain.repository.ProductsRepository
import javax.inject.Inject

/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * GetProducts use case to get all products from data layer.
 */
open class GetProducts @Inject constructor(
    private val repository: ProductsRepository,
    executor: ExecutionThread
) : SingleUseCase<List<Category>>(executor) {
    public override fun buildUseCaseSingle(): Single<List<Category>> {
        return repository.getCategories()
    }

}