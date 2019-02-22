package me.mfathy.mobiquity.ui.products

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * BaseViewModel to manage disposable for all sub view models.
 */
open class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    /**
     * Add Rx disposables to be easy for clean up.
     */
    open fun addDisposables(disposable: Disposable) {
        disposables.add(disposable)
    }

    /**
     * Remove disposables.
     */
    open fun clearDisposables() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}