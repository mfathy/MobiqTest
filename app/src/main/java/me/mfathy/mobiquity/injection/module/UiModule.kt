package me.mfathy.mobiquity.injection.module

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.mfathy.mobiquity.domain.executor.ExecutionThread
import me.mfathy.mobiquity.executor.ExecutionSchedulers
import me.mfathy.mobiquity.injection.scope.ViewScope
import me.mfathy.mobiquity.ui.products.ProductsActivity
import me.mfathy.mobiquity.ui.products.ProductsFragment

/**
 * Dagger module to provide UI and activities dependencies.
 */
@Module
abstract class UiModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesExecutionThread(): ExecutionThread = ExecutionSchedulers(
            Schedulers.io(),
            AndroidSchedulers.mainThread()
        )
    }


    @ViewScope
    @ContributesAndroidInjector
    abstract fun contributesProductsActivity(): ProductsActivity

    @ViewScope
    @ContributesAndroidInjector
    abstract fun contributesProductsFragment(): ProductsFragment

}