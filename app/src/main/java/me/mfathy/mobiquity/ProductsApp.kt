package me.mfathy.mobiquity

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import me.mfathy.mobiquity.injection.component.DaggerApplicationComponent

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * ProductsApp dagger application implementation.
 */
class ProductsApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder().create(this)
}