package me.mfathy.mobiquity.injection

import dagger.Module
import dagger.Provides
import me.mfathy.mobiquity.data.store.remote.ProductsRemoteDataStore
import org.mockito.Mockito.mock

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideRemoteDataStore(): ProductsRemoteDataStore {
        return mock(ProductsRemoteDataStore::class.java)
    }

}