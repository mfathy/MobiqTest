package me.mfathy.mobiquity.injection

import dagger.Binds
import dagger.Module
import dagger.Provides
import me.mfathy.mobiquity.data.mapper.DataMapper
import me.mfathy.mobiquity.data.repository.ProductsDataRepository
import me.mfathy.mobiquity.domain.repository.ProductsRepository

@Module
abstract class TestDataModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataMapper(): DataMapper = DataMapper()
    }

    @Binds
    abstract fun bindDataRepository(dataRepository: ProductsDataRepository): ProductsRepository

}