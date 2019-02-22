package me.mfathy.mobiquity.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import me.mfathy.mobiquity.domain.repository.ProductsRepository
import me.mfathy.mobiquity.injection.module.ViewModelsModule
import me.mfathy.mobiquity.test.TestApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        TestApplicationModule::class,
        TestDataModule::class,
        ViewModelsModule::class,
        TestUiModule::class,
        TestRemoteModule::class]
)
interface TestApplicationComponent {

    fun repository(): ProductsRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent
    }

    fun inject(application: TestApplication)

}