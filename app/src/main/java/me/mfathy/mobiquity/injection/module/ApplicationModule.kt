package me.mfathy.mobiquity.injection.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import me.mfathy.mobiquity.ProductsApp


/**
 * Dagger application module to provide app mContext.
 */
@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: ProductsApp): Context

    @Binds
    abstract fun bindApplication(application: ProductsApp): Application

}
