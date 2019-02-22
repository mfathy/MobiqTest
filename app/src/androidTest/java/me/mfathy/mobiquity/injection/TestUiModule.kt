package me.mfathy.mobiquity.injection

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
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
abstract class TestUiModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesExecutionThread(): ExecutionThread = ExecutionSchedulers(
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @ViewScope
    @ContributesAndroidInjector
    abstract fun contributesProductsActivity(): ProductsActivity

    @ViewScope
    @ContributesAndroidInjector
    abstract fun contributesProductsFragment(): ProductsFragment
}