package me.mfathy.mobiquity.injection

import dagger.Module
import dagger.Provides
import me.mfathy.mobiquity.domain.repository.ProductsRepository
import org.mockito.Mockito.mock
import javax.inject.Singleton

@Module
object TestDataModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideDataRepository(): ProductsRepository {
        return mock(ProductsRepository::class.java)
    }

}