package me.mfathy.mobiquity.injection.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import me.mfathy.mobiquity.ProductsApp
import me.mfathy.mobiquity.injection.module.*
import javax.inject.Singleton

/**
 * Dagger application components
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ViewModelsModule::class,
        DataModule::class,
        RemoteModule::class,
        UiModule::class
    ]
)

interface ApplicationComponent : AndroidInjector<ProductsApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ProductsApp>()

}