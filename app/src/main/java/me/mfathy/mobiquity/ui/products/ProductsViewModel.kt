package me.mfathy.mobiquity.ui.products

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import me.mfathy.mobiquity.domain.interactor.products.GetProducts
import me.mfathy.mobiquity.domain.model.Category
import me.mfathy.mobiquity.state.DataState
import javax.inject.Inject

/**
 * Product list view model.
 */
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProducts
) : BaseViewModel() {

    private val productsLiveData: MutableLiveData<DataState> = MutableLiveData()

    override fun onCleared() {
        clearDisposables()
        super.onCleared()
    }

    fun getProductsLiveData(): MutableLiveData<DataState> = productsLiveData

    /**
     * Fetches a products list.
     */
    fun fetchProductsList() {
        productsLiveData.postValue(DataState.OnLoading)
        addDisposables(getProductsUseCase.execute(GetProductsObserver()))
    }

    inner class GetProductsObserver : DisposableSingleObserver<List<Category>>() {
        override fun onSuccess(t: List<Category>) {
            productsLiveData.postValue(DataState.OnSuccess(t))
        }

        override fun onError(e: Throwable) {
            productsLiveData.postValue(DataState.OnError(e))
        }
    }
}
