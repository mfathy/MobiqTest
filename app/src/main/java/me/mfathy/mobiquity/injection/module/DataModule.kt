package me.mfathy.mobiquity.injection.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import me.mfathy.mobiquity.data.mapper.DataMapper
import me.mfathy.mobiquity.data.repository.ProductsDataRepository
import me.mfathy.mobiquity.domain.repository.ProductsRepository

/**
 * Dagger module to provide data repository dependencies.
 */
@Module
abstract class DataModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataMapper(): DataMapper = DataMapper()
    }

    @Binds
    abstract fun bindDataRepository(dataRepository: ProductsDataRepository): ProductsRepository
}